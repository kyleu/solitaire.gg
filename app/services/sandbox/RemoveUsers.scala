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
    val usersFuture = Database.query(new Query[Seq[(UUID, Int)]] {
      override def sql = """
        select
          u.id, count(r.*) cnt
        from
          users u left outer join requests r on u.id = r.user_id
        where
          u.id != '00000000-0000-0000-0000-000000000000'
        group by
          u.id
      """
      override def reduce(rows: Iterator[Row]) = rows.map { row =>
        (row.as[UUID]("id"), row.as[Long]("cnt").toInt)
      }.toSeq
    })

    usersFuture.flatMap { users =>
      val dudUsers = users.filter(_._2 == 1)

      Future.sequence(dudUsers.map(u => UserService.remove(u._1))).map { ok =>
        s"Ok: [${dudUsers.length} of ${users.length}] users removed."
      }
    }
  }
}
