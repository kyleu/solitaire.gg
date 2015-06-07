package models.database.queries.ddl

import models.database.Statement

case object CreateGameTable extends Statement {
  override val sql = """
    create table games (
      id uuid not null primary key,
      seed int not null,
      rules character varying(128) not null,
      status character varying(128) not null,
      player uuid not null,
      moves integer not null default 0,
      undos integer not null default 0,
      redos integer not null default 0,
      created timestamp not null default now(),
      completed timestamp
    ) with (oids=false);

    create index games_player_idx on games using btree (player);
  """
}
