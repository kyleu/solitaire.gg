package models.database.queries.ddl

import models.database.Statement

case object CreateAdHocQueriesTable extends Statement {
  override val sql = """
    create table adhoc_queries (
      id uuid not null primary key,
      title text not null,
      author uuid not null,
      sql text not null,
      params text[],
      created timestamp not null,
      updated timestamp not null
    ) with (oids=false)
  """
}
