package models.database.queries

import java.util.UUID

import models.database.{ Row, SingleRowQuery, Query }

object ReportQueries {
  case object ListTables extends Query[List[String]] {
    override def sql = BaseQueries.trim("""
      select t.table_name as tn
      from information_schema.tables as t
      where table_catalog = 'solitaire' and table_schema = 'public' and table_type = 'BASE TABLE'
      order by table_name
    """)
    override def reduce(rows: Iterator[Row]) = rows.map(row => row.as[String]("tn")).toList
  }

  case class CountTable(t: String) extends SingleRowQuery[(String, Long)] {
    override def sql = "select count(*) as c from " + t
    override def map(row: Row) = t -> row.as[Long]("c")
  }

  case class GameCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    val playerClause = if (userIds.isEmpty) { "" } else { s" where player in (${userIds.map(id => "?").mkString(", ")})" }
    override def sql = "select player, count(*) as c from games" + playerClause + " group by player"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      UUID.fromString(row.as[String]("player")) -> row.as[Long]("c").toInt
    }.toMap
  }

  case class WinCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    val playerClause = if (userIds.isEmpty) { "" } else { s" and player in (${userIds.map(id => "?").mkString(", ")})" }
    override def sql = "select player, count(*) as c from games where status = 'win'" + playerClause + " group by player"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      UUID.fromString(row.as[String]("player")) -> row.as[Long]("c").toInt
    }.toMap
  }

  case class RequestCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    val playerClause = if (userIds.isEmpty) { "" } else { s" where user_id in (${userIds.map(id => "?").mkString(", ")})" }
    override def sql = "select user_id, count(*) as c from requests" + playerClause + " group by user_id"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      UUID.fromString(row.as[String]("user_id")) -> row.as[Long]("c").toInt
    }.toMap
  }
}