package models.ddl

import models.database.Statement

case object CreateGamesTable extends Statement {
  override val sql = """
    create table games (
      id uuid not null primary key,
      seed int not null,
      rules character varying(128) not null,
      status character varying(128) not null,
      player uuid not null,
      cards integer not null default 0,
      moves integer not null default 0,
      undos integer not null default 0,
      redos integer not null default 0,
      created timestamp not null default now(),
      first_move timestamp,
      completed timestamp,
      logged timestamp
    ) with (oids=false);

    create index games_player_idx on games using btree (player);

    alter table games add constraint games_users_fk foreign key (player) references users (id) on update no action on delete no action;
  """
}
