package models.ddl

case object CreateDataArchiveTable extends CreateTableStatement("data_archive") {
  override val sql = s"""
    create table $tableName (
      table_name character varying(64) not null,
      day date not null,
      archived_count integer not null,
      archived timestamp without time zone,
      constraint ${tableName}_pk primary key (table_name, day)
    ) with (oids = false);
  """
}
