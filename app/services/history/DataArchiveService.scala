package services.history

import models.database.queries.history.DataArchiveQueries
import models.database.{ SingleRowQuery, Query, Row }
import models.history.DataArchiveCount
import org.joda.time.{ LocalDateTime, LocalDate }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object DataArchiveService {
  val tables = Seq(
    ("Games", "games"),
    ("Game Moves", "game_moves"),
    ("Game Cards", "game_cards"),
    ("Requests", "requests"),
    ("Users", "users")
  )

  def getAll = Database.query(DataArchiveQueries.getAll)
  def getByDay(day: LocalDate) = Database.query(DataArchiveQueries.GetArchiveCountsByDay(day))

  def process(day: LocalDate) = {
    val affectedGames = Database.query(new Query[Seq[String]] {
      override def sql = "select id from games where created >= ? and created < ?"
      override def values = Seq(day, day.plusDays(1))
      override def reduce(rows: Iterator[Row]) = rows.map(_.as[String]("id")).toSeq
    })

    affectedGames.flatMap { games =>
      val gamesCount = DataArchiveCount("games", day, games.size, new LocalDateTime())

      val movesCount = Database.query(new SingleRowQuery[Int] {
        override def sql = s"select count(*) as c from game_moves where game_id in (${games.map(x => "?").mkString(",")})"
        override def values = games
        override def map(row: Row): Int = row.as[Long]("c").toInt
      }).map(moves => DataArchiveCount("game_moves", day, moves, new LocalDateTime()))

      val cardsCount = Database.query(new SingleRowQuery[Int] {
        override def sql = s"select count(*) as c from game_cards where game_id in (${games.map(x => "?").mkString(",")})"
        override def values = games
        override def map(row: Row): Int = row.as[Long]("c").toInt
      }).map(cards => DataArchiveCount("game_cards", day, cards, new LocalDateTime()))

      val requestsCount = Database.query(new SingleRowQuery[Int] {
        override def sql = s"select count(*) as c from requests where started >= ? and started < ?"
        override def values = Seq(day, day.plusDays(1))
        override def map(row: Row): Int = row.as[Long]("c").toInt
      }).map(requests => DataArchiveCount("requests", day, requests, new LocalDateTime()))

      val usersCount = requestsCount.flatMap { unused =>
        Database.query(new SingleRowQuery[Int] {
          override def sql = s"select count(*) as c from users u where (select count(*) from requests where user_id = u.id) = 0"
          override def map(row: Row): Int = row.as[Long]("c").toInt
        }).map(requests => DataArchiveCount("users", day, requests, new LocalDateTime()))
      }

      val ret = Future.sequence(Seq(movesCount, cardsCount, requestsCount, usersCount)).map(gamesCount +: _)

      ret.map { archives =>
        Database.execute(DataArchiveQueries.insertBatch(archives))
        archives
      }
    }
  }
}
