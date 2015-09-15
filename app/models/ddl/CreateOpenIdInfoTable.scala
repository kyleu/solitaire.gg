package models.ddl

case object CreateOpenIdInfoTable extends CreateTableStatement("openid_info") {
  override val sql = s"""
    create table $tableName
    (
       provider character varying(64) not null,
       key text not null,
       id text not null,
       attributes text not null,
       created timestamp without time zone not null,
       constraint ${tableName}_pk primary key (provider, key)
    ) with (oids = false);
  """
}
