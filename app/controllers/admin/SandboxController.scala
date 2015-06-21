package controllers.admin

import akka.util.Timeout
import controllers.BaseController
import models.database.queries.history.RequestLogQueries
import org.joda.time.{ LocalDate, LocalDateTime }
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.DailyMetricService
import services.database.Database
import services.scheduled.ScheduledTask
import services.test.{ SolverTests, TestService }

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.duration._

object SandboxController {
  val sandboxes = Seq(
    "list" -> "List available sandboxes (this one).",
    "scheduled" -> "Run the scheduled task.",
    "solver-test" -> "Infinite stress test for the poor solver.",
    "error-mail" -> "Send the error email.",
    "backfill-metrics" -> "Backfill missing daily metrics."
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
      case "solver-test" => runSolverStressTest()
      case "backfill-metrics" => runBackfillMetrics()
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

  private[this] def runSolverStressTest() = Future.successful {
    var runsCompleted = 0

    def runTest(): Future[Unit] = {
      Future(TestService.run(new SolverTests().all)).flatMap { result =>
        runsCompleted += 1
        log.info(s"COMPLEEEEETE! $runsCompleted runs completed!")
        runTest()
      }
    }

    runTest()
    Ok("OK, churning...")
  }

  private[this] def runBackfillMetrics() = {
    def getDays(d: LocalDate) = {
      val today = new LocalDate()
      val start = (d.getYear * 365) + d.getDayOfYear
      val end = (today.getYear * 365) + today.getDayOfYear
      val numDays = end - start
      Future.successful((0 to numDays).map(i => today.minusDays(i)))
    }

    for {
      startDay <- Database.query(RequestLogQueries.GetEarliestDay)
      days <- getDays(startDay)
      result <- Future.sequence(days.map(d => DailyMetricService.getMetrics(d)))
    } yield Ok(result.map(x => s"${x._1}: ${x._2.size} metrics.").mkString("\n"))
  }
}
