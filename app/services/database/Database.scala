package services.database

import com.github.mauricio.async.db.{QueryResult, Connection, Configuration}
import com.github.mauricio.async.db.pool.{PoolConfiguration, ConnectionPool}
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory
import models.database.{Statement, RawQuery}
import utils.Logging

import scala.concurrent.duration._
import scala.concurrent.{Future, Await}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Database extends Logging {
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
    log.debug(s"Executing statement [${statement.sql}] with values [${statement.values.mkString("(", ", ", ")")}].")
    conn.sendPreparedStatement(prependComment(statement, statement.sql), statement.values).map(_.rowsAffected.toInt)
  }

  def query[A](query: RawQuery[A], conn: Connection = pool): Future[A] = {
    log.debug(s"Executing query [${query.sql}] with values [${query.values.mkString("(", ", ", ")")}].")
    conn.sendPreparedStatement(prependComment(query, query.sql), query.values).map { r =>
      query.handle(r.rows.getOrElse(throw new IllegalStateException()))
    }
  }

  def raw(name: String, sql: String, conn: Connection = pool): Future[QueryResult] = {
    log.debug(s"Executing raw query [$sql].")
    conn.sendQuery(prependComment(name, sql))
  }

  def close() = {
    Await.result(pool.close, 5.seconds)
    true
  }

  private[this] val factory = new PostgreSQLConnectionFactory( configuration )
  private[this] val pool = new ConnectionPool(factory, PoolConfiguration.Default)

  private[this] def prependComment(obj: Object, sql: String) = s"/* ${obj.getClass.getSimpleName.replace("$", "")} */ $sql"
}
