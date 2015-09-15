package models.ddl

case object CreateUserProfilesTable extends CreateTableStatement("user_profiles") {
  override val sql = s"""
    create table $tableName (
      provider character varying(64) not null,
      key text not null,
      email character varying(256),
      first_name character varying(512),
      last_name character varying(512),
      full_name character varying(512),
      avatar_url character varying(512),
      created timestamp not null,
      constraint pk_user_profiles primary key (provider, key)
    ) with (oids=false);

    create index ${tableName}_email_idx on $tableName using btree (email collate pg_catalog."default");
  """
}
