package controllers.admin

import akka.util.Timeout
import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.twirl.api.Html
import services.sandbox._
import services.scheduled.ScheduledTask
import utils.{Application, DateUtils}

import scala.concurrent.Future
import scala.concurrent.duration._

object SandboxController {
  val sandboxes = Seq(
    Scratchpad,
    ScreenshotCreator,
    RunScheduledTask,
    SendErrorEmail,
    BackfillMetrics,
    HtmlSandbox
  )
}

@javax.inject.Singleton
class SandboxController @javax.inject.Inject() (override val app: Application, scheduledTask: ScheduledTask) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def defaultSandbox() = sandbox("list")

  RunScheduledTask.scheduledTask = Some(scheduledTask)

  def sandbox(key: String) = withAdminSession(key) { implicit request =>
    val sandbox = SandboxController.sandboxes.find(_.id == key).getOrElse(throw new IllegalStateException())
    if (sandbox == HtmlSandbox) {
      Future.successful(Ok(views.html.admin.test.sandbox(java.util.UUID.randomUUID())))
    } else {
      sandbox.run(app).map { result =>
        Ok(Html(result))
      }
    }
  }
}
