package models.queries.audit

import java.util.UUID

import models.audit.UserFeedback
import models.queries.BaseQueries
import models.database.{Query, Row}
import java.time.LocalDateTime

object UserFeedbackNoteQueries extends BaseQueries[UserFeedback.FeedbackNote] {
  override protected val tableName = "user_feedback_notes"
  override protected val columns = Seq("id", "feedback_id", "author_id", "content", "occurred")
  override protected val searchColumns = Seq("id::text", "author_id::text", "active_game_id::text", "content")

  val insert = Insert

  case class GetUserFeedbackNotes(feedbackIds: Seq[UUID]) extends Query[List[UserFeedback.FeedbackNote]] {
    override val sql = getSql(whereClause = Some(if (feedbackIds.nonEmpty) {
      s"feedback_id in (${feedbackIds.map(_ => "?").mkString(", ")})"
    } else {
      "1 = 1"
    }))
    override val values = feedbackIds
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val feedbackId = row.as[UUID]("feedback_id")
    val authorId = row.as[UUID]("author_id")
    val content = row.as[String]("content")
    val occurred = row.as[LocalDateTime]("occurred")
    UserFeedback.FeedbackNote(id, feedbackId, authorId, content, occurred)
  }

  override protected def toDataSeq(f: UserFeedback.FeedbackNote) = Seq[Any](f.id, f.feedbackId, f.authorId, f.content, f.occurred)
}
