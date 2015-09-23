package models.ddl

case object CreateUserStatisticsTable extends CreateTableStatement("user_statistics") {
  override val sql = s"""
    create table $tableName (
      id uuid not null primary key,
      joined timestamp not null default now(),
      wins int not null default 0,
      losses int not null default 0,
      total_duration_ms bigint not null default 0,
      total_moves integer not null default 0,
      total_undos integer not null default 0,
      total_redos integer not null default 0,
      last_win timestamp default null,
      last_loss timestamp default null,
      current_win_streak integer not null default 0,
      max_win_streak integer not null default 0,
      current_loss_streak integer not null default 0,
      max_loss_streak integer not null default 0
    ) with (oids=false);

    alter table $tableName add constraint ${tableName}_users_fk foreign key (id)
      references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
