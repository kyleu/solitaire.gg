package services.spark

import org.apache.spark.{ SparkConf, SparkContext }
import utils.{ Config, Logging }

object SparkService extends Logging {
  private var ctx: Option[SparkContext] = None

  private[this] def context = ctx.getOrElse(throw new IllegalStateException("Not started."))

  def rddTextfile(path: String) = context.textFile(path)

  def start(master: String, port: Int) = {
    if(ctx.isDefined) {
      throw new IllegalStateException("Already initialized.")
    }
    log.info(s"Starting Spark service with master [$master], with the UI avaliable at port [$port].")
    val conf = getConfig(master, port)
    val c = new SparkContext(conf)
    ctx = Some(c)
    c
  }

  def stop() = {
    log.info("Spark service is stopping.")
    ctx.foreach(_.stop())
    ctx = None
  }

  private[this] def getConfig(master: String, port: Int) = new SparkConf()
    .setAppName(Config.projectName)
    .setMaster(master)
    .set("spark.ui.port", port.toString)
}
