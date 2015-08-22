package models.queries.report

import java.util.UUID

import models.database.{ Query, Row }

object ReportQueries {
  private[this] def playerClause(name: String, userIds: Seq[UUID]) = if (userIds.isEmpty) {
    ""
  } else {
    s" where $name in (${userIds.map(id => "?").mkString(", ")})"
  }

  case class GameCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    override def sql = s"select player, count(*) as c from games${playerClause("player", userIds)} group by player"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      row.as[UUID]("player") -> row.as[Long]("c").toInt
    }.toMap
  }

  case class WinCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    override def sql = s"select player, count(*) as c from games${playerClause("player", userIds)} and status = 'win' group by player"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      row.as[UUID]("player") -> row.as[Long]("c").toInt
    }.toMap
  }

  case class RequestCountForUsers(userIds: Seq[UUID]) extends Query[Map[UUID, Int]] {
    override def sql = s"select user_id, count(*) as c from requests${playerClause("user_id", userIds)} group by user_id"
    override def values = userIds
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      row.as[UUID]("user_id") -> row.as[Long]("c").toInt
    }.toMap
  }
}
