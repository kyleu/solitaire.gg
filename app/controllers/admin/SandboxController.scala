package controllers.admin

import akka.util.Timeout
import controllers.BaseController
import models.auth.AuthenticationEnvironment
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.scheduled.ScheduledTask

import services.sandbox._
import utils.DateUtils

import scala.concurrent.Future
import scala.concurrent.duration._

object SandboxController {
  val sandboxes = Seq(
    Scratchpad,
    ScreenshotCreator,
    ExportStatic,
    RunScheduledTask,
    SolverStressTest,
    SendErrorEmail,
    BackfillGames,
    BackfillMetrics
  )
}

@javax.inject.Singleton
class SandboxController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment,
    scheduledTask: ScheduledTask
) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def defaultSandbox() = sandbox("list")

  RunScheduledTask.scheduledTask = Some(scheduledTask)
  ExportStatic.messagesApi = Some(messagesApi)

  def sandbox(key: String) = withAdminSession(key) { implicit request =>
    val sandbox = SandboxController.sandboxes.find(_.id == key).getOrElse(throw new IllegalStateException())

    sandbox.run().map { result =>
      Ok(result)
    }
  }

  private[this] def runErrorMail() = Future.successful(
    views.html.email.severeErrorHtml(
      "Error Message",
      "Test Context",
      Some(new Exception("Text Exception")),
      None,
      DateUtils.now
    )
  )
}
