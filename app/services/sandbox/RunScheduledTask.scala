package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.scheduled.ScheduledTask
import utils.ApplicationContext

object RunScheduledTask extends SandboxTask {
  override def id = "run-scheduled-task"
  override def description = "Run the scheduled task."

  var scheduledTask: Option[ScheduledTask] = None

  override def run(ctx: ApplicationContext) = {
    val task = scheduledTask.getOrElse(throw new IllegalStateException())
    task.go(true).map { ret =>
      ret.map(x => s"${x._1}: ${x._2.getOrElse("No progress")}").mkString("\n")
    }
  }
}
