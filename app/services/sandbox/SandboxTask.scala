package services.sandbox

import enumeratum.values.{StringEnum, StringEnumEntry}
import services.export.ExportService
import services.wiki.WikiService
import utils.{Application, Logging}

import scala.concurrent.Future

sealed abstract class SandboxTask(val value: String, val description: String) extends StringEnumEntry with Logging {
  def run(ctx: Application): Future[String]
}

object SandboxTask extends StringEnum[SandboxTask] {
  case object BackfillMetrics extends SandboxTask("backfill-metrics", "Backfill missing daily metrics.") with BackfillMetricsLogic
  case object HtmlSandbox extends SandboxTask("html-sandbox", "Shows the rendered contents of sandbox.scala.html.") {
    override def run(ctx: Application) = throw new IllegalStateException("Not meant to be run directly.")
  }
  case object ExportStatic extends SandboxTask("export-static", "Export static templates and supporting files.") {
    override def run(ctx: Application) = ExportService.go(ctx)
  }
  case object RunScheduledTask extends SandboxTask("run-scheduled-task", "Runs the scheduled task.") with RunScheduledTaskLogic
  case object Scratchpad extends SandboxTask("scratchpad", "A one-off I don't feel like putting anywhere else.") with ScratchpadLogic
  case object ScreenshotCreator extends SandboxTask("screenshot-creator", "Generates screenshots for completed games.") with ScreenshotCreatorLogic
  case object SendErrorEmail extends SandboxTask("send-error-email", "Sends the error email, obviously.") with SendErrorEmailLogic
  case object SolverStressTest extends SandboxTask("solver-stress-test", "Spins up a bunch of threads to solve games.") with SolverStressTestLogic
  case object Wiki extends SandboxTask("wiki", "Exports the solitaire.gg wiki.") {
    override def run(ctx: Application) = WikiService.run(ctx)
  }

  override val values = findValues
}
