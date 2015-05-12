package models.database.queries

import com.github.mauricio.async.db.RowData
import models.database.{ SingleRowQuery, Statement }

object DdlQueries {
  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "select exists (select * from information_schema.tables WHERE table_name = ?);"
    override val values = tableName :: Nil
    override def map(row: RowData) = row("exists").asInstanceOf[Boolean]
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

      create index accounts_name_idx on accounts using btree (name collate pg_catalog."default");
    """)
  }

  case object CreateGameTable extends Statement {
    override val sql = trim("""
      create table games (
        id uuid primary key,
        seed int not null,
        rules character varying(128) not null,
        status character varying(128) not null,
        player uuid not null,
        moves integer not null default 0,
        undos integer not null default 0,
        redos integer not null default 0,
        created timestamp not null default now(),
        completed timestamp
      ) with (
        oids=false
      );

      create index games_player_idx on games using btree (player);
    """)
  }

  private[this] def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}
