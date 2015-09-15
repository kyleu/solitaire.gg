package models.ddl

case object CreateOAuth1InfoTable extends CreateTableStatement("oauth1_info") {
  override val sql = s"""
    create table $tableName (
       provider character varying(64) not null,
       key text not null,
       token text not null,
       secret text not null,
       created timestamp without time zone not null,
       constraint ${tableName}_pk primary key (provider, key)
    ) with (oids = false);
  """
}
