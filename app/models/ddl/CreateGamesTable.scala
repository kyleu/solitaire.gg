package models.ddl

case object CreateGamesTable extends CreateTableStatement("games") {
  override val sql = s"""
    create table $tableName (
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

    create index ${tableName}_player_idx on $tableName using btree (player);

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_fk
      foreign key (player) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
