package models.queries

import com.simple.jdub.{Row, SingleRowQuery, Statement}

object DdlQueries {
  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "select exists (select * from information_schema.tables WHERE table_name = ?);"
    override val values = tableName :: Nil
    override def map(row: Row) = row.boolean("exists").get
  }

  case class TruncateTable(tableName: String) extends Statement {
    val sql = s"TRUNCATE $tableName"
    val values = Nil
  }

  case class DropTable(tableName: String) extends Statement {
    override val sql = "drop table if exists \"?\""
    override val values = Seq(tableName)
  }

  case object CreateAccountTable extends Statement {
    override val sql = trim(s"""
      create table accounts (
        id uuid primary key,
        email character varying(256) not null
        created timestamp not null
      ) with (
        oids=false
      );

      create index accounts_email_idx
        on accounts
        using btree (email collate pg_catalog."default");
    """)
    override val values = Nil
  }

  case object CreateGameTable extends Statement {
    override val sql = trim(s"""
      create table games (
        id uuid primary key,
        variant character varying(128) not null,
        accounts uuid[] not null default array[]::uuid[],
        created timestamp not null
      ) with (
        oids=false
      );

      create index games_accounts_idx
        on games
        using gin (accounts);
    """)
    override val values = Nil
  }
}
