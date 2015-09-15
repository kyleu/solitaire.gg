package models.ddl

case object CreateOAuth2InfoTable extends CreateTableStatement("oauth2_info") {
  override val sql = s"""
    create table $tableName (
       provider character varying(64) not null,
       key text not null,
       access_token text not null,
       token_type character varying(64),
       expires_in integer,
       refresh_token character varying(64),
       params text,
       created timestamp without time zone,
       constraint ${tableName}_pk primary key (provider, key)
    ) with (oids = false);
  """
}
