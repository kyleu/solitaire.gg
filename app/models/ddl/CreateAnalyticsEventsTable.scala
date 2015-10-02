package models.ddl

case object CreateAnalyticsEventsTable extends CreateTableStatement("analytics_events") {
  override val sql = s"""
    create table $tableName (
      id uuid not null primary key,
      event_type character varying(64) not null,
      device uuid not null,
      data json not null,
      created timestamp not null
    ) with (oids=false);

    create index ${tableName}_device_idx on $tableName using btree (device);
  """
}
