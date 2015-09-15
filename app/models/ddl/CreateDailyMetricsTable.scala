package models.ddl

case object CreateDailyMetricsTable extends CreateTableStatement("daily_metrics") {
  override val sql = s"""
    create table $tableName
    (
       day date not null,
       metric character varying(128) not null,
       value bigint not null default 0,
       measured timestamp without time zone not null,
       constraint ${tableName}_pk primary key (day, metric)
    ) with (oids = false);
  """
}
