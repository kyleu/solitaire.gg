package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.audit.UserFeedback
import models.queries.audit.{ UserFeedbackNoteQueries, UserFeedbackQueries }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.{ DateUtils, ApplicationContext }

@javax.inject.Singleton
class FeedbackController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def list(key: String, q: String, sortBy: String, page: Int) = withAdminSession("list-" + key) { implicit request =>
    implicit val identity = request.identity

    val feedbackList = for {
      count <- Database.query(UserFeedbackQueries.searchCount(q))
      feedbacks <- Database.query(UserFeedbackQueries.search(q, getOrderClause(sortBy), Some(page)))
      notes <- Database.query(UserFeedbackNoteQueries.GetUserFeedbackNotes(feedbacks.map(_.id)))
    } yield {
      count -> feedbacks.map(f => f -> notes.filter(_.feedbackId == f.id).sortBy(x => DateUtils.toMillis(x.occurred)))
    }

    feedbackList.map { fn =>
      val filtered = key match {
        case "all" => fn._2
        case "open" => fn._2.filterNot(_._2.exists(_.content.toLowerCase.startsWith("close")))
        case "resolved" => fn._2.filter(_._2.exists { x =>
          val lc = x.content.toLowerCase
          lc.startsWith("close") && lc.contains("resolved")
        })
        case "praise" => fn._2.filter(_._2.exists { x =>
          val lc = x.content.toLowerCase
          lc.startsWith("close") && lc.contains("praise")
        })
        case _ => throw new IllegalStateException()
      }
      Ok(views.html.admin.feedback.feedbackList(key, q, sortBy, if (key == "all") { fn._1 } else { filtered.size }, page, filtered))
    }
  }

  def feedbackNoteForm(feedbackId: UUID) = withAdminSession("note.form") { implicit request =>
    implicit val identity = request.identity
    Database.query(UserFeedbackQueries.getById(feedbackId)).map { feedback =>
      Ok(views.html.admin.feedback.feedbackForm(feedback.getOrElse(throw new IllegalStateException())))
    }
  }

  def feedbackNotePost(feedbackId: UUID) = withAdminSession("note.post") { implicit request =>
    implicit val identity = request.identity
    val body = request.body.asFormUrlEncoded.getOrElse(throw new IllegalStateException())
    val contentField = body.get("content")
    val content = contentField.flatMap(_.headOption).getOrElse(throw new IllegalStateException())
    val note = UserFeedback.FeedbackNote(UUID.randomUUID, feedbackId, identity.id, content, DateUtils.now)
    Database.execute(UserFeedbackNoteQueries.insert(note)).map { unused =>
      Redirect(controllers.admin.routes.FeedbackController.list("all"))
    }
  }

  def removeFeedback(id: UUID) = withAdminSession("remove") { implicit request =>
    Database.execute(UserFeedbackQueries.remove(Seq(id))).map { ok =>
      Redirect(controllers.admin.routes.FeedbackController.list("all", "", "occurred", 0))
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case x => x
  }
}
