package services.database

import com.github.mauricio.async.db.pool.{ ConnectionPool, PoolConfiguration }
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory
import com.github.mauricio.async.db.{ Connection, QueryResult }
import models.database.{ RawQuery, Statement }
import nl.grons.metrics.scala.FutureMetrics
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.{ Config, Logging }
import utils.metrics.Instrumented

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

object Database extends Logging with Instrumented with FutureMetrics {
  def open() = {
    val healthCheck = pool.sendQuery("select now()")
    healthCheck.onFailure {
      case x => throw new IllegalStateException("Cannot connect to database.", x)
    }
    Await.result(healthCheck.map(r => r.rowsAffected == 1.toLong), 5.seconds)
  }

  def transaction[A](f: (Connection) => Future[A], conn: Connection = pool): Future[A] = conn.inTransaction(c => f(c))

  def execute(statement: Statement, conn: Connection = pool): Future[Int] = {
    val name = statement.getClass.getSimpleName.replaceAllLiterally("$", "")
    log.debug(s"Executing statement [$name] with SQL [${statement.sql}] with values [${statement.values.mkString(", ")}].")
    val ret = timing("execute." + name) {
      conn.sendPreparedStatement(prependComment(statement, statement.sql), statement.values).map(_.rowsAffected.toInt)
    }
    ret.onFailure {
      case x: Throwable => log.error(s"Error [${x.getClass.getSimpleName}] encountered while executing statement [$name].", x)
    }
    ret
  }

  def query[A](query: RawQuery[A], conn: Connection = pool): Future[A] = {
    val name = query.getClass.getSimpleName.replaceAllLiterally("$", "")
    log.debug(s"Executing query [$name] with SQL [${query.sql}] with values [${query.values.mkString(", ")}].")
    val ret = timing("query." + name) {
      conn.sendPreparedStatement(prependComment(query, query.sql), query.values).map { r =>
        query.handle(r.rows.getOrElse(throw new IllegalStateException()))
      }
    }
    ret.onFailure {
      case x: Throwable => log.error(s"Error [${x.getClass.getSimpleName}] encountered while executing query [$name].", x)
    }
    ret
  }

  def raw(name: String, sql: String, conn: Connection = pool): Future[QueryResult] = {
    log.debug(s"Executing raw query [$name] with SQL [$sql].")
    val ret = timing("rawquery." + name.getClass.getSimpleName.replaceAllLiterally("$", "")) {
      conn.sendQuery(prependComment(name, sql))
    }
    ret.onFailure {
      case x: Throwable => log.error(s"Error [${x.getClass.getSimpleName}] encountered while executing raw query [$name].", x)
    }
    ret
  }

  def close() = {
    Await.result(pool.close, 5.seconds)
    true
  }

  private[this] val factory = new PostgreSQLConnectionFactory(Config.databaseConfiguration)
  private[this] val poolConfig = new PoolConfiguration(maxObjects = 100, maxIdle = 10, maxQueueSize = 1000)
  private[this] val pool = new ConnectionPool(factory, poolConfig)

  private[this] def prependComment(obj: Object, sql: String) = s"/* ${obj.getClass.getSimpleName.replace("$", "")} */ $sql"
}
