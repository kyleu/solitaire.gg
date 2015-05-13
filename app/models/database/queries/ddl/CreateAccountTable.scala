package models.database.queries.ddl

import models.database.Statement

case object CreateAccountTable extends Statement {
  override val sql = DdlQueries.trim("""
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
