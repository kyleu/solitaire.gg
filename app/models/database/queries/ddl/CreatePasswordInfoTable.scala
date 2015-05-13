package models.database.queries.ddl

import models.database.Statement

case object CreatePasswordInfoTable extends Statement {
  override val sql = DdlQueries.trim("""
    create table password_info
    (
       provider character varying(64),
       key text,
       hasher character varying(64),
       password character varying(256),
       salt character varying(256),
       created timestamp without time zone,
       constraint pk_password_info primary key (provider, key)
    )
    with (
      oids = false
    )
  """)
}
