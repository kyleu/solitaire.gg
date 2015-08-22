package models.queries.ddl

import models.database.Statement

object CreateGameCardsTable extends Statement {
  override def sql: String = """
    create table game_cards (
      card_id uuid not null primary key,
      game_id uuid not null,
      sort_order integer not null,
      rank character(1) not null,
      suit character(1) not null
    ) with (oids=false);

    alter table game_cards add constraint game_cards_games_fk foreign key (game_id) references games (id) on update no action on delete no action;
    create index game_cards_game_idx on game_cards using btree (game_id);

    create unique index game_cards_game_sort_order_idx on game_cards using btree (game_id, sort_order);
  """
}
