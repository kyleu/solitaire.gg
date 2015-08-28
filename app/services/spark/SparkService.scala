package services.spark

import org.apache.spark.{ SparkConf, SparkContext }
import utils.{ Config, Logging }

object SparkService extends Logging {
  private var ctx: Option[SparkContext] = None

  def context = ctx.getOrElse(throw new IllegalStateException("Not started."))

  def start(master: String) = {
    if(ctx.isDefined) {
      throw new IllegalStateException("Already initialized.")
    }
    log.info(s"Starting Spark service with master [$master].")
    val conf = new SparkConf().setAppName(Config.projectName).setMaster(master)
    val c = new SparkContext(conf)
    ctx = Some(c)
    c
  }

  def stop() = {
    log.info("Spark service is stopping.")
    ctx.foreach(_.stop())
    ctx = None
  }
}
