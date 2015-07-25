package models.database.queries.ddl

import models.database.Statement

case object CreateRowCountTable extends Statement {
  override val sql = """
    create table row_count
    (
       table_name character varying(64) not null,
       total integer not null,
       current integer not null,
       reaped integer not null,
       updated timestamp without time zone not null,
       constraint pk_row_count primary key (table_name)
    ) with (oids = false);
  """
}
