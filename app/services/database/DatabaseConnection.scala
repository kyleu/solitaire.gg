package services.database

import java.util.concurrent.Callable

import com.simple.jdub.{ Statement, RawQuery, Database }
import play.api.Play
import utils.{ Logging, Config }
import utils.metrics.{ Instrumented, Checked }

object DatabaseConnection extends Logging with Instrumented {
  case class DatabaseException(queryType: String, sql: String, values: Seq[Any], ex: Exception) extends Exception(ex) {
    private[this] val valStr = values.mkString(", ")
    override def getMessage = "Exception [" + ex.getClass.getSimpleName + "] encountered processing " + queryType + " [" + sql + "] with values [" + valStr + "]."
  }

  private[this] val db = {
    val cfg = Play.current.configuration
    val url = cfg.getString("db.default.url").getOrElse(throw new IllegalArgumentException("No database url provided."))
    val username = cfg.getString("db.default.username").getOrElse(Config.projectId)
    val password = cfg.getString("db.default.password").getOrElse(Config.projectId)
    val name = Some(Config.projectId)
    val healthCheckRegistry = Some(Checked.healthCheckRegistry)
    val maxSize = 32
    val maxWait = 10000
    Database.connect(url, username, password, name, maxWait = maxWait, maxSize = maxSize, healthCheckRegistry = healthCheckRegistry)
  }

  def open() = {
    DatabaseSchema.update()
  }

  def close() = {
    db.close()
  }

  def transaction[A](f: => A) = {
    db.transactionScope(f)
  }

  def query[A](query: RawQuery[A]) = try {
    val timer = metricRegistry.timer(query.getClass.getName)
    timer.time(new Callable[A] {
      override def call() = db.query(query)
    })
  } catch {
    case ex: Exception => throw new DatabaseException("query", query.sql, query.values, ex)
  }

  def execute(statement: Statement) = try {
    val timer = metricRegistry.timer(statement.getClass.getName)
    timer.time(new Callable[Int] {
      override def call() = db.execute(statement)
    })
  } catch {
    case ex: Exception => throw new DatabaseException("statement", statement.sql, statement.values, ex)
  }
}
