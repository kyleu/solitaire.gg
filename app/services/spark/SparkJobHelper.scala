package services.spark

import org.apache.spark.rdd.RDD
import play.api.libs.json.Json

object SparkJobHelper {
  def processOne(rdd: RDD[String]) = {
    rdd.map(getJsonEntry).groupBy(x => x).mapValues(_.size).foldByKey(0)(_ + _)
  }

  def getJsonEntry(line: String) = {
    val json = Json.parse(line)
    (json \ "eventName").as[String]
  }
}
