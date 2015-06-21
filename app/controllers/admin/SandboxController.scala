package controllers.admin

import akka.util.Timeout
import controllers.BaseController
import org.joda.time.LocalDateTime
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.scheduled.ScheduledTask

import scala.concurrent.Future
import scala.concurrent.duration._

object SandboxController {
  val sandboxes = Seq(
    "list" -> "List available sandboxes (this one).",
    "scheduled" -> "Run the scheduled task.",
    "error-mail" -> "Send the error email."
  )
}

@javax.inject.Singleton
class SandboxController @javax.inject.Inject() (override val messagesApi: MessagesApi, scheduledTask: ScheduledTask) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def defaultSandbox() = sandbox("list")

  def sandbox(key: String) = withAdminSession { implicit request =>
    key match {
      case "list" => listSandboxes()
      case "scheduled" => runScheduledTask()
      case "error-mail" => runErrorMail()
      case x => throw new IllegalArgumentException(s"Invalid sandbox [$x].")
    }
  }

  private[this] def listSandboxes() = {
    val ret = SandboxController.sandboxes.map(x => s"${x._1}: ${x._2}").mkString("\n")
    Future.successful(Ok(ret))
  }

  private[this] def runScheduledTask() = scheduledTask.go().map { ret =>
    Ok(ret.map(x => s"${x._1}: ${x._2.getOrElse("No progress")}").mkString("\n"))
  }

  private[this] def runErrorMail() = Future.successful(
    Ok(views.html.email.severeErrorHtml("Error Message", "Test Context", Some(new Exception("Text Exception")), None, new LocalDateTime()))
  )
}
