package models.queries

import com.simple.jdub.{Row, SingleRowQuery, Statement}

object DdlQueries {
  val tables = Map("accounts" -> CreateAccountTable, "games" -> CreateGameTable)

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

  case object EnableUuidIndex extends Statement {
    override def sql: String = """
      DO $$
      BEGIN

      CREATE OPERATOR CLASS _uuid_ops DEFAULT FOR TYPE _uuid USING gin AS
      OPERATOR 1 &&(anyarray, anyarray),
      OPERATOR 2 @>(anyarray, anyarray),
      OPERATOR 3 <@(anyarray, anyarray),
      OPERATOR 4 =(anyarray, anyarray),
      FUNCTION 1 uuid_cmp(uuid, uuid),
      FUNCTION 2 ginarrayextract(anyarray, internal, internal),
      FUNCTION 3 ginqueryarrayextract(anyarray, internal, smallint, internal, internal, internal, internal),
      FUNCTION 4 ginarrayconsistent(internal, smallint, anyarray, integer, internal, internal, internal, internal),
      STORAGE uuid;

      EXCEPTION
      WHEN duplicate_object THEN
      RAISE NOTICE 'error: %', SQLERRM;
      END;
      $$;
    """
    override def values: Seq[Any] = Nil
  }

  case object CreateAccountTable extends Statement {
    override val sql = trim(s"""
      create table accounts (
        id uuid primary key,
        name character varying(256) not null,
        created timestamp not null
      ) with (
        oids=false
      );

      create index accounts_name_idx
        on accounts
        using btree (name collate pg_catalog."default");
    """)
    override val values = Nil
  }

  case object CreateGameTable extends Statement {
    override val sql = trim(s"""
      create table games (
        id uuid primary key,
        seed int not null,
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
