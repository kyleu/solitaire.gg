package models.ddl

object CreateGameCardsTable extends CreateTableStatement("game_cards") {
  override def sql: String = s"""
    create table $tableName (
      card_id uuid not null primary key,
      game_id uuid not null,
      sort_order integer not null,
      rank character(1) not null,
      suit character(1) not null
    ) with (oids=false);

    alter table $tableName add constraint ${tableName}_${CreateGamesTable.tableName}_fk
      foreign key (game_id) references ${CreateGamesTable.tableName} (id) on update no action on delete no action;

    create index ${tableName}_game_idx on $tableName using btree (game_id);

    create unique index ${tableName}_game_id_sort_order_idx on $tableName using btree (game_id, sort_order);
  """
}
