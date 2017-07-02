package models.queries

import models.database._

object BaseQueries {
  val pageSize = 100
  def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}

trait BaseQueries[T] {
  protected def tableName: String = "_invalid_"
  protected def idColumns = Seq("id")
  protected def columns: Seq[String]
  protected lazy val quotedColumns = columns.mkString(", ")
  protected def columnPlaceholders = columns.map(_ => "?").mkString(", ")
  protected def searchColumns: Seq[String]

  protected def fromRow(row: Row): T
  protected def toDataSeq(t: T): Seq[Any]

  protected lazy val insertSql = s"insert into $tableName ($quotedColumns) values ($columnPlaceholders)"

  protected def updateSql(updateColumns: Seq[String], additionalUpdates: Option[String] = None) = BaseQueries.trim(s"""
    update $tableName set ${updateColumns.map(x => s"$x = ?").mkString(", ")}${additionalUpdates.map(x => s", $x").getOrElse("")} where $idWhereClause
  """)

  protected def getSql(
    whereClause: Option[String] = None, groupBy: Option[String] = None, orderBy: Option[String] = None, limit: Option[Int] = None, offset: Option[Int] = None
  ) = BaseQueries.trim(s"""
    select $quotedColumns from $tableName ${whereClause.map(x => s" where $x").getOrElse("")}
    ${groupBy.map(x => s" group by $x").getOrElse("")} ${orderBy.map(x => s" order by $x").getOrElse("")}
    ${limit.map(x => s" limit $x").getOrElse("")} ${offset.map(x => s" offset $x").getOrElse("")}
  """)

  protected case class GetById(override val values: Seq[Any]) extends FlatSingleRowQuery[T] {
    override val sql = s"select $quotedColumns from $tableName where $idWhereClause"
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  protected def getBySingleId(id: Any) = GetById(Seq(id))

  protected class SeqQuery(additionalSql: String, override val values: Seq[Any] = Nil) extends Query[Seq[T]] {
    override val sql = s"select $quotedColumns from $tableName $additionalSql"
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  protected case class GetAll(orderBy: Option[String] = None, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    orderBy.map(" order by " + _).getOrElse("") + limit.map(" limit " + _).getOrElse("") + offset.map(" offset " + _).getOrElse("")
  )

  protected case class Insert(model: T) extends Statement {
    override val sql = insertSql
    override val values = toDataSeq(model)
  }

  protected case object Truncate extends Statement {
    override val sql = s"truncate $tableName"
  }

  protected case class InsertBatch(models: Seq[T]) extends Statement {
    private[this] val valuesClause = models.map(_ => s"($columnPlaceholders)").mkString(", ")
    override val sql = s"insert into $tableName ($quotedColumns) values $valuesClause"
    override val values = models.flatMap(toDataSeq)
  }

  protected case class RemoveById(override val values: Seq[Any]) extends Statement {
    override val sql = s"delete from $tableName where $idWhereClause"
  }

  protected case class Count(override val sql: String, override val values: Seq[Any]) extends Query[Int] {
    override def reduce(rows: Iterator[Row]) = rows.next().as[Long]("c").toInt
  }

  protected class SearchCount(q: String, groupBy: Option[String] = None) extends Count(sql = {
    val searchWhere = if (q.isEmpty) { "" } else { "where " + searchColumns.map(c => s"lower($c) like lower(?)").mkString(" or ") }
    s"select count(*) as c from $tableName $searchWhere ${groupBy.map(x => s" group by $x").getOrElse("")}"
  }, values = if (q.isEmpty) { Seq.empty } else { searchColumns.map(_ => s"%$q%") })

  protected case class Search(q: String, orderBy: String, limit: Option[Int], offset: Option[Int]) extends SeqQuery("") {
    private[this] val whereClause = if (q.isEmpty) { None } else { Some(searchColumns.map(c => s"lower($c) like lower(?)").mkString(" or ")) }
    override val sql = getSql(whereClause, None, Some(orderBy), limit, offset)
    override val values = if (q.isEmpty) { Seq.empty } else { searchColumns.map(c => s"%$q%") }
  }

  protected case class CountWhere(whereClause: Option[String]) extends SingleRowQuery[Int] {
    override def sql = s"select count(*) as c from $tableName${whereClause.map(" where " + _)}"
    override def map(row: Row) = row.as[Int]("c")
  }

  private def idWhereClause = idColumns.map(c => s"$c = ?").mkString(" and ")
}
