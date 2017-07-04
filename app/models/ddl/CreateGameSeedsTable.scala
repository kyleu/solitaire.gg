package models.ddl

case object CreateGameSeedsTable extends CreateTableStatement("game_seeds") {
  override val sql = s"""
    create table $tableName (
      rules character varying(128) not null,
      seed int not null,
      games int not null default 0,
      wins int not null default 0,
      moves int not null default 0,

      first_player uuid default null,
      first_moves integer default null,
      first_elapsed_ms integer default null,
      first_occurred timestamp default null,

      fastest_player uuid default null,
      fastest_moves integer default null,
      fastest_elapsed_ms integer default null,
      fastest_occurred timestamp default null,

      constraint ${tableName}_pk primary key (rules, seed)
    ) with (oids=false);

    create index ${tableName}_rules_idx on $tableName using btree (rules);

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_first_fk
      foreign key (first_player) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_fastest_fk
      foreign key (fastest_player) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
