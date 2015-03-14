package services.database

import com.simple.jdub.{SingleRowQuery, Row, Query, Database}
import utils.Config
import utils.metrics.Checked

object DatabaseConnection {
  def open() = {
    db.query(new SingleRowQuery[Int] {
      override def sql = "select 0 as num"
      override def values = Nil
      override def map(row: Row): Int = row.int("num").get
    })
  }

  def close() = {
    db.close()
  }

  private val db = {
    val url = "jdbc:postgresql://localhost/scalataire"
    val username = "scalataire"
    val password = "omgWTFdragonz!"
    val name = Some(Config.projectId)
    val healthCheckRegistry = Some(Checked.healthCheckRegistry)
    Database.connect(url, username, password, name, healthCheckRegistry = healthCheckRegistry)
  }
}
