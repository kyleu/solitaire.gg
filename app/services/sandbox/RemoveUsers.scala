package services.sandbox

import java.util.UUID

import models.database.{ Row, Query }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.user.UserService
import utils.ApplicationContext

import scala.concurrent.Future

object RemoveUsers extends SandboxTask {
  override def id = "remove-users"
  override def description = "Removes users with only one request and no games."
  override def run(ctx: ApplicationContext) = {
    val usersFuture = Database.query(new Query[Seq[(UUID, Int, Int)]] {
      override def sql = """
        select
          u.id, count(r.*) rc, count(g.*) gc
        from
          users u
            left outer join requests r on u.id = r.user_id
            left outer join games g on u.id = g.player
        where
          u.id != '00000000-0000-0000-0000-000000000000'
        group by
          u.id
      """
      override def reduce(rows: Iterator[Row]) = rows.map { row =>
        (row.as[UUID]("id"), row.as[Long]("rc").toInt, row.as[Long]("gc").toInt)
      }.toSeq
    })

    usersFuture.flatMap { users =>
      val dudUsers = users.filter(u => u._2 == 1 && u._3 == 0).map(_._1)

      dudUsers.foldLeft(Future.successful(Unit)) { (f, u) =>
        f.flatMap { ok =>
          UserService.remove(u).map(x => Unit)
        }
      }.map { ok =>
        s"Ok: [${dudUsers.length} of ${users.length}] users removed."
      }
    }
  }
}
