package models.ddl

case object CreateGameStatisticsTable extends CreateTableStatement("game_statistics") {
  override val sql = s"""
    create table $tableName (
      rules character varying(128) not null primary key,
      played int not null default 0,
      wins int not null default 0,
      losses int not null default 0,
      min_duration_ms bigint default null,
      max_duration_ms bigint default null,
      total_duration_ms bigint not null default 0,
      min_moves integer default null,
      max_moves integer default null,
      total_moves integer not null default 0,
      total_undos integer not null default 0,
      total_redos integer not null default 0,
      last_win timestamp default null,
      last_loss timestamp default null
    ) with (oids=false);
  """
}
