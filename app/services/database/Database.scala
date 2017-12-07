package services.database

import models.database.{DatabaseConfig, RawQuery, Statement}
import util.Logging

import scala.concurrent.Future

object Database extends JdbcDatabase("app", "database")

trait Database[Conn] extends Logging {
  protected[this] def key: String

  def transaction[A](f: Conn => Future[A], conn: Option[Conn]): Future[A]
  def execute(statement: Statement, conn: Option[Conn] = None): Future[Int]
  def query[A](query: RawQuery[A], conn: Option[Conn] = None): Future[A]

  def close(): Boolean

  private[this] var config: Option[DatabaseConfig] = None
  def getConfig = config.getOrElse(throw new IllegalStateException("Database not open."))

  protected[this] def start(cfg: DatabaseConfig) = {
    config = Some(cfg)
  }

  protected[this] def prependComment(obj: Object, sql: String) = s"/* ${obj.getClass.getSimpleName.replace("$", "")} */ $sql"
}
