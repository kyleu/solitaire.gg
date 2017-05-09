package models.ddl

case object CreateUsersTable extends CreateTableStatement("users") {
  override val sql = s"""
    create table $tableName (
      id uuid primary key,
      username character varying(256),
      email character varying(2048),
      prefs json NOT NULL,
      created timestamp not null
    ) with (oids=false);

    create index ${tableName}_username_idx on $tableName using btree (username);
    create index ${tableName}_email_idx on $tableName using btree (email);
  """
}
