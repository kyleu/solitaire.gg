package models.sandbox

import enumeratum._
import services.export.ExportService
import services.sandbox._
import services.wiki.WikiService
import utils.{Application, Logging}
import utils.FutureUtils.defaultContext

import scala.concurrent.Future

sealed abstract class SandboxTask(val description: String) extends EnumEntry with Logging {
  final def run(app: Application): Future[SandboxTask.Result] = {
    log.info(s"Running sandbox task [$this]...")
    val startMs = System.currentTimeMillis
    val result = call(app).map { r =>
      val res = SandboxTask.Result(this, "OK", r, (System.currentTimeMillis - startMs).toInt)
      log.info(s"Completed sandbox task [$this] with status [${res.status}] in [${res.elapsed}ms].")
      res
    }
    result
  }
  def call(app: Application): Future[String]
}

object SandboxTask extends Enum[SandboxTask] {
  case class Result(task: SandboxTask, status: String = "OK", result: String, elapsed: Int)

  case object BackfillMetrics extends SandboxTask("Backfill missing daily metrics.") with BackfillMetricsLogic
  case object HtmlSandbox extends SandboxTask("Shows the rendered contents of sandbox.scala.html.") {
    override def call(ctx: Application) = throw new IllegalStateException("Not meant to be run directly.")
  }
  case object ExportStatic extends SandboxTask("Export static templates and supporting files.") {
    override def call(ctx: Application) = ExportService.go(ctx)
  }
  case object RunScheduledTask extends SandboxTask("Runs the scheduled task.") with RunScheduledTaskLogic
  case object Scratchpad extends SandboxTask("A one-off I don't feel like putting anywhere else.") with ScratchpadLogic
  case object ScreenshotCreator extends SandboxTask("Generates screenshots for completed games.") with ScreenshotCreatorLogic
  case object SendErrorEmail extends SandboxTask("Sends the error email, obviously.") with SendErrorEmailLogic
  case object SolverStressTest extends SandboxTask("Spins up a bunch of threads to solve games.") with SolverStressTestLogic
  case object Wiki extends SandboxTask("Exports the solitaire.gg wiki.") {
    override def call(ctx: Application) = WikiService.run(ctx)
  }

  override val values = findValues
}
