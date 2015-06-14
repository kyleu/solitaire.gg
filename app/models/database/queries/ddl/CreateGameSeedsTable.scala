package models.database.queries.ddl

import models.database.Statement

case object CreateGameSeedsTable extends Statement {
  override val sql = """
    create table game_seeds (
      rules character varying(128) not null,
      seed int not null,
      player uuid not null,
      moves integer not null default 0,
      elapsed_ms integer not null default 0,
      completed timestamp,
      constraint pk_game_seed primary key (rules, seed)
    ) with (oids=false);

    create index games_rules_idx on game_seeds using btree (rules);
  """
}
