package models.ddl

case object CreateGameSeedsTable extends CreateTableStatement("game_seeds") {
  override val sql = s"""
    create table $tableName (
      rules character varying(128) not null,
      seed int not null,
      games int not null,
      wins int not null,
      player uuid,
      moves integer default 0,
      elapsed_ms integer,
      completed timestamp,
      constraint ${tableName}_pk primary key (rules, seed)
    ) with (oids=false);

    create index ${tableName}_rules_idx on $tableName using btree (rules);

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_fk
      foreign key (player) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
