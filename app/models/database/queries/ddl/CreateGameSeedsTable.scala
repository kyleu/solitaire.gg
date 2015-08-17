package models.database.queries.ddl

import models.database.Statement

case object CreateGameSeedsTable extends Statement {
  override val sql = """
    create table game_seeds (
      rules character varying(128) not null,
      seed int not null,
      games int not null,
      wins int not null,
      player uuid,
      moves integer default 0,
      elapsed_ms integer,
      completed timestamp,
      constraint pk_game_seed primary key (rules, seed)
    ) with (oids=false);

    create index games_rules_idx on game_seeds using btree (rules);

    alter table game_seeds add constraint game_seeds_users_fk foreign key (player) references users (id) on update no action on delete no action;
  """
}
