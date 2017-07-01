package services.sandbox

import utils.FutureUtils.defaultContext
import services.scheduled.ScheduledTask
import utils.Application

trait RunScheduledTaskLogic {
  var scheduledTask: Option[ScheduledTask] = None

  def call(ctx: Application) = scheduledTask.getOrElse(throw new IllegalStateException()).go(true).map { ret =>
    ret.map(x => s"${x._1}: ${x._2.getOrElse("No progress")}").mkString("\n")
  }
}
