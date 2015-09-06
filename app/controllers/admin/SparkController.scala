package controllers.admin

import controllers.BaseController
import play.api.inject.ApplicationLifecycle
import services.spark.{ SparkJobHelper, SparkService }
import utils.{ Formatter, ApplicationContext }

import scala.concurrent.Future

@javax.inject.Singleton
class SparkController @javax.inject.Inject() (override val ctx: ApplicationContext, lifecycle: ApplicationLifecycle) extends BaseController {
  def index = withAdminSession("index") { implicit request =>
    if(ctx.config.sparkEnabled) {
      Future.successful(Ok(views.html.admin.spark.index()))
    } else {
      Future.successful(Ok("Spark not enabled."))
    }
  }

  def run(job: String) = withAdminSession(s"run-$job") { implicit request =>
    if(ctx.config.sparkEnabled) {
      val startMs = System.currentTimeMillis()

      val logData = SparkService.rddTextfile("/Users/kyle/Temp/spark/s3/2015/7/1/*.gz")

      val rdd = job match {
        case "one" => SparkJobHelper.processOne(logData)
        case _ => throw new IllegalArgumentException(s"Unknown Spark job [$job].")
      }

      val result = rdd.collect().sortBy(_._1)
      val total = result.map(_._2).sum

      val elapsed = (System.currentTimeMillis - startMs).toInt
      val ret = s"Processed [${Formatter.withCommas(total)}] log entries in [${Formatter.withCommas(elapsed)}ms]:\n${result.map { x =>
        "  " + x._1 + ": " + Formatter.withCommas(x._2)
      }.mkString("\n")}"
      Future.successful(Ok(ret))
    } else {
      Future.successful(Ok("Spark not enabled."))
    }
  }

}
