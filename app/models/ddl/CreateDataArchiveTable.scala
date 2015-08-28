package models.ddl

import models.database.Statement

case object CreateDataArchiveTable extends Statement {
  override val sql = """
    create table data_archive
    (
       table_name character varying(64) not null,
       day date not null,
       archived_count integer not null,
       archived timestamp without time zone,
       constraint pk_data_archive primary key (table_name, day)
    ) with (oids = false);
  """
}
