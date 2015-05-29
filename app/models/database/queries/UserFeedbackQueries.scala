package models.database.queries

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.database.{ FlatSingleRowQuery, Query, Statement }
import models.user.UserFeedback
import org.joda.time.LocalDateTime
import utils.DateUtils

object UserFeedbackQueries extends BaseQueries[UserFeedback] {
  override protected val tableName = "user_feedback"
  override protected val columns = Seq("id", "user_id", "active_game_id", "feedback", "occurred")
  override protected val searchColumns = Seq("id::text", "user_id::text", "active_game_id::text", "feedback")

  case class CreateUserFeedback(f: UserFeedback) extends Statement {
    override val sql = insertSql
    override val values = Seq[Any](f.id, f.userId, f.activeGameId, f.feedback, DateUtils.toSqlTimestamp(f.occurred))
  }

  case class GetUserFeedbackByUser(id: UUID, sortBy: String) extends Query[List[UserFeedback]] {
    override val sql = getSql("player = ?", Some("?"))
    override val values = Seq(id, sortBy)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val userId = row("user_id") match { case s: String => UUID.fromString(s) }
    val activeGameId = row("active_game_id") match { case s: String => Some(UUID.fromString(s)); case _ => None }
    val feedback = row("feedback") match { case s: String => s }
    val occurred = row("occurred") match { case ldt: LocalDateTime => ldt }
    UserFeedback(id, userId, activeGameId, feedback, occurred)
  }
}
