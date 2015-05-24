package models.database.queries.ddl

import models.database.Statement

object CreateGameMovesTable extends Statement {
  override def sql: String = """
    create table game_moves (
      game_id uuid not null,
      sequence integer not null,
      player_id uuid not null,
      move character varying(20) not null,
      cards uuid[] not null default '{}'::uuid[],
      src character varying(30),
      tgt character varying(30),
      occurred timestamp without time zone not null
    ) with (oids=false);

    alter table game_moves add constraint game_moves_pk primary key (game_id, sequence);

    alter table game_moves add constraint game_moves_games_fk foreign key (game_id) references games (id) on update no action on delete no action;
    create index game_moves_game_idx on game_moves using btree (game_id);

    alter table game_moves add constraint game_moves_users_fk foreign key (player_id) references users (id) on update no action on delete no action;
    create index game_cards_player_idx on game_moves using btree (player_id);
  """
}
