package models.database.queries

trait BaseQueries {
  protected def tableName: String
  protected def idColumn: String = "id"
  protected def columns: Seq[String]

  protected lazy val insertSql = s"insert into $tableName (${columns.mkString(", ")}) values (${columns.map(x => "?").mkString(", ")})"

  protected def updateSql(updateColumns: Seq[String], additionalUpdates: Option[String] = None) = trim(s"""
    update $tableName set ${updateColumns.map(x => x + " = ?").mkString(", ")}${additionalUpdates.map(x => ", " + x).getOrElse("")} where $idColumn = ?
  """)

  protected lazy val getByIdSql = s"select ${columns.mkString(", ")} from $tableName where $idColumn = ?"

  protected def countSql(whereClause: String) = s"select count(*) as c from $tableName where $whereClause"

  protected def getSql(whereClause: String, orderBy: Option[String] = None, limit: Option[Int] = None, offset: Option[Int] = None) = trim(s"""
    select ${columns.mkString(", ")} from $tableName
    where $whereClause
    ${orderBy.map(x => " order by " + x).getOrElse("")}
    ${limit.map(x => " limit " + x).getOrElse("")}
    ${offset.map(x => " offset " + x).getOrElse("")}
  """)

  protected lazy val removeByIdSql = s"delete from $tableName where $idColumn = ?"

  protected def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}
