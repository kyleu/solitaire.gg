package models.queries

import com.simple.jdub.{Row, SingleRowQuery, Statement}

object DdlQueries {
  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "SELECT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = ?);"
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
      CREATE TABLE accounts (
        id uuid primary key,
        email character varying(255) NOT NULL
        created timestamp NOT NULL
      ) WITH (
        OIDS=FALSE
      );

      CREATE INDEX accounts_email_idx
        ON accounts
        USING btree (email COLLATE pg_catalog."default");
    """)
    override val values = Nil
  }

  case object CreateGameTable extends Statement {
    override val sql = trim(s"""
      CREATE TABLE games (
        id uuid primary key,
        accounts uuid[] NOT NULL DEFAULT ARRAY[]::uuid[],
        created timestamp NOT NULL
      ) WITH (
        OIDS=FALSE
      );

      CREATE INDEX games_accounts_idx
        ON games
        USING GIN (accounts);
    """)
    override val values = Nil
  }
}
