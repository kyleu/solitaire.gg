package models.database.queries

import com.github.mauricio.async.db.RowData
import models.database.{ SingleRowQuery, Statement }

object DdlQueries {
  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "select exists (select * from information_schema.tables WHERE table_name = ?);"
    override val values = tableName :: Nil
    override def map(row: RowData) = row("exists").asInstanceOf[Boolean]
  }

  case object UuidIndexEnabled extends SingleRowQuery[Boolean] {
    override def sql = "select count(*) as c from pg_am am, pg_opclass opc where opc.opcmethod = am.oid and opc.opcname = '_uuid_ops'"
    override def map(row: RowData) = row("c").asInstanceOf[Int] == 1
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
  }

  case object CreateAccountTable extends Statement {
    override val sql = trim("""
      create table accounts (
        id uuid primary key,
        name character varying(256) not null,
        role character varying(64) not null,
        games_played integer not null default 0,
        last_game_started timestamp,
        created timestamp not null
      ) with (
        oids=false
      );

      create index accounts_name_idx
        on accounts
        using btree (name collate pg_catalog."default");
    """)
  }

  case object CreateGameTable extends Statement {
    override val sql = trim("""
      create table games (
        id uuid primary key,
        seed int not null,
        rules character varying(128) not null,
        status character varying(128) not null,
        accounts uuid[] not null default array[]::uuid[],
        moves integer not null default 0,
        undos integer not null default 0,
        redos integer not null default 0,
        created timestamp not null default now(),
        completed timestamp
      ) with (
        oids=false
      );

      create index games_accounts_idx
        on games
        using gin (accounts);
    """)
  }

  private[this] def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}
