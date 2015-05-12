package services.database

import com.github.mauricio.async.db.{ QueryResult, Connection, Configuration }
import com.github.mauricio.async.db.pool.{ PoolConfiguration, ConnectionPool }
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory
import models.database.{ Statement, RawQuery }
import nl.grons.metrics.scala.FutureMetrics
import utils.Logging
import utils.metrics.Instrumented

import scala.concurrent.duration._
import scala.concurrent.{ Future, Await }
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Database extends Logging with Instrumented with FutureMetrics {
  private val configuration = new Configuration(
    host = play.api.Play.current.configuration.getString("db.host").getOrElse(throw new IllegalStateException()),
    username = play.api.Play.current.configuration.getString("db.username").getOrElse(throw new IllegalStateException()),
    password = Some(play.api.Play.current.configuration.getString("db.password").getOrElse(throw new IllegalStateException())),
    database = Some("solitaire"),
    port = 5432
  )

  def open() = {
    val healthCheck = pool.sendQuery("select now()")
    healthCheck.onFailure {
      case x => throw new IllegalStateException("Cannot connect to database.", x)
    }
    Await.result(healthCheck.map(r => r.rowsAffected == 1), 5.seconds)
  }

  def transaction[A](f: (Connection) => Future[A], conn: Connection = pool): Future[A] = conn.inTransaction(c => f(c))

  def execute(statement: Statement, conn: Connection = pool): Future[Int] = {
    val name = statement.getClass.getSimpleName.replaceAllLiterally("$", "")
    log.debug(s"Executing statement [$name] with SQL [${statement.sql}] with values [${statement.values.mkString("(", ", ", ")")}].")
    val ret = timing("execute." + name) {
      conn.sendPreparedStatement(prependComment(statement, statement.sql), statement.values).map(_.rowsAffected.toInt)
    }
    ret.onFailure {
      case x: Throwable => log.error("Error [" + x.getClass.getSimpleName + "] encountered while executing statement [" + name + "].", x)
    }
    ret
  }

  def query[A](query: RawQuery[A], conn: Connection = pool): Future[A] = {
    val name = query.getClass.getSimpleName.replaceAllLiterally("$", "")
    log.debug(s"Executing query [$name] with SQL [${query.sql}] with values [" + query.values.mkString("(", ", ", ")") + "].")
    val ret = timing("query." + name) {
      conn.sendPreparedStatement(prependComment(query, query.sql), query.values).map { r =>
        query.handle(r.rows.getOrElse(throw new IllegalStateException()))
      }
    }
    ret.onFailure {
      case x: Throwable => log.error("Error [" + x.getClass.getSimpleName + "] encountered while executing query [" + name + "].", x)
    }
    ret
  }

  def raw(name: String, sql: String, conn: Connection = pool): Future[QueryResult] = {
    log.debug(s"Executing raw query [$name] with SQL [$sql].")
    val ret = timing("rawquery." + name.getClass.getSimpleName.replaceAllLiterally("$", "")) {
      conn.sendQuery(prependComment(name, sql))
    }
    ret.onFailure {
      case x: Throwable => log.error("Error [" + x.getClass.getSimpleName + "] encountered while executing raw query [" + name + "].", x)
    }
    ret
  }

  def close() = {
    Await.result(pool.close, 5.seconds)
    true
  }

  private[this] val factory = new PostgreSQLConnectionFactory(configuration)
  private[this] val pool = new ConnectionPool(factory, PoolConfiguration.Default)

  private[this] def prependComment(obj: Object, sql: String) = s"/* ${obj.getClass.getSimpleName.replace("$", "")} */ $sql"
}
