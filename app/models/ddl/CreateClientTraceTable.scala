package models.ddl

case object CreateClientTraceTable extends CreateTableStatement("client_trace") {
  override val sql = s"""
    create table $tableName (
      id uuid not null primary key,
      trace_type character varying(64) not null default 'web-request',
      player uuid not null,
      data json not null,
      created timestamp not null
    ) with (oids=false);
  """
}
