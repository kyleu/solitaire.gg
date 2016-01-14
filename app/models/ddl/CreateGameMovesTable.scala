package models.ddl

object CreateGameMovesTable extends CreateTableStatement("game_moves") {
  override def sql: String = s"""
    create table $tableName (
      game_id uuid not null,
      sequence integer not null,
      player_id uuid not null,
      move character varying(20) not null,
      cards uuid[] not null default '{}'::uuid[],
      src character varying(30),
      tgt character varying(30),
      occurred timestamp without time zone not null
    ) with (oids=false);

    alter table $tableName add constraint ${tableName}_pk primary key (game_id, sequence);

    alter table $tableName add constraint ${tableName}_${CreateGamesTable.tableName}_fk
      foreign key (game_id) references ${CreateGamesTable.tableName} (id) on update no action on delete no action;

    create index ${tableName}_game_idx on $tableName using btree (game_id);

    create index ${tableName}_player_idx on $tableName using btree (player_id);
  """
}
