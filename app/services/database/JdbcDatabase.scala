package services.database

import java.sql.Connection
import java.util.Properties

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.prometheus.client.Histogram
import models.database.jdbc.Queryable
import models.database.{DatabaseConfig, RawQuery, Statement}

import scala.concurrent.Future
import scala.util.control.NonFatal

abstract class JdbcDatabase(override val key: String, configPrefix: String) extends Database[Connection] with Queryable {
  protected[this] lazy val sqlHistogram = Histogram.build(
    s"${util.Config.projectId}_${key}_database",
    s"Database metrics for [$key]."
  ).labelNames("method", "name").register()

  private[this] def time[A](method: String, name: String)(f: => A) = {
    val ctx = sqlHistogram.labels(method, name).startTimer()
    try { f } finally { ctx.close() }
  }

  private[this] var ds: Option[HikariDataSource] = None
  private[this] def source = ds.getOrElse(throw new IllegalStateException("Database not initialized."))

  def open(cfg: play.api.Configuration) = {
    ds.foreach(_ => throw new IllegalStateException("Database already initialized."))

    val config = DatabaseConfig.fromConfig(cfg, configPrefix)
    val properties = new Properties

    val poolConfig = new HikariConfig(properties) {
      setPoolName(util.Config.projectId + "." + key)
      setJdbcUrl(config.url)
      setUsername(config.username)
      setPassword(config.password.getOrElse(""))
      setConnectionTimeout(10000)
      setMinimumIdle(1)
      setMaximumPoolSize(32)
    }

    val poolDataSource = new HikariDataSource(poolConfig)

    ds = Some(poolDataSource)

    start(config)
  }

  override def transaction[A](f: Connection => Future[A], conn: Option[Connection] = None) = {
    val connection = source.getConnection
    connection.setAutoCommit(false)
    try {
      val result = f(connection)
      connection.commit()
      result
    } catch {
      case NonFatal(x) =>
        connection.rollback()
        throw x
    } finally {
      connection.close()
    }
  }

  override def execute(statement: Statement, conn: Option[Connection]) = {
    val connection = conn.getOrElse(source.getConnection)
    try {
      time("execute", statement.getClass.getName) { executeUpdate(connection, statement) }
    } catch {
      case NonFatal(x) =>
        log.errorThenThrow(s"Error executing [${statement.getClass.getName}] with [${statement.values.size}] values and sql [${statement.sql}].", x)
    } finally {
      if (conn.isEmpty) { connection.close() }
    }
  }

  override def query[A](query: RawQuery[A], conn: Option[Connection]) = {
    val connection = conn.getOrElse(source.getConnection)
    try {
      time("query", query.getClass.getName)(apply(connection, query))
    } catch {
      case NonFatal(x) => log.errorThenThrow(s"Error running query [${query.getClass.getName}] with [${query.values.size}] values and sql [${query.sql}].", x)
    } finally {
      if (conn.isEmpty) { connection.close() }
    }
  }

  def withConnection[T](f: (Connection) => T) = {
    val conn = source.getConnection()
    try { f(conn) } finally { conn.close() }
  }

  override def close() = {
    ds.foreach(_.close())
    true
  }
}
