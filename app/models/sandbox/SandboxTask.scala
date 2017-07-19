package models.sandbox

import enumeratum.values._
import services.database.BackupRestore
import services.export.ExportService
import services.sandbox._
import services.wiki.WikiService
import util.{Application, EnumWithDescription, Logging}
import util.FutureUtils.defaultContext

import scala.concurrent.Future

sealed abstract class SandboxTask(override val value: String, override val description: String) extends EnumWithDescription with Logging {
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

object SandboxTask extends StringEnum[SandboxTask] {
  case class Result(task: SandboxTask, status: String = "OK", result: String, elapsed: Int)

  case object HtmlSandbox extends SandboxTask("HtmlSandbox", "Shows the rendered contents of sandbox.scala.html.") {
    override def call(ctx: Application) = throw new IllegalStateException("Not meant to be run directly.")
  }
  case object ExportStatic extends SandboxTask("ExportStatic", "Export static templates and supporting files.") {
    override def call(ctx: Application) = ExportService.go(ctx)
  }
  case object DatabaseBackup extends SandboxTask("DatabaseBackup", "Backs up the database.") {
    override def call(app: Application) = Future.successful(BackupRestore.backup())
  }
  case object DatabaseRestore extends SandboxTask("DatabaseRestore", "Restores the database.") {
    override def call(app: Application) = Future.successful(BackupRestore.restore("TODO"))
  }

  case object RunScheduledTask extends SandboxTask("RunScheduledTask", "Runs the scheduled task.") with RunScheduledTaskLogic
  case object Scratchpad extends SandboxTask("Scratchpad", "A one-off I don't feel like putting anywhere else.") with ScratchpadLogic
  case object ScreenshotCreator extends SandboxTask("ScreenshotCreator", "Generates screenshots for completed games.") with ScreenshotCreatorLogic
  case object SendErrorEmail extends SandboxTask("SendErrorEmail", "Sends the error email, obviously.") with SendErrorEmailLogic
  case object SolverStressTest extends SandboxTask("SolverStressTest", "Spins up a bunch of threads to solve games.") with SolverStressTestLogic
  case object GameStatsReset extends SandboxTask("GameStatsReset", "Resets and recalculates all game statistics.") with GameStatsResetLogic
  case object Wiki extends SandboxTask("Wiki", "Exports the solitaire.gg wiki.") {
    override def call(ctx: Application) = WikiService.run(ctx)
  }

  override val values = findValues
}
