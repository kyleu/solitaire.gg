package models.ddl

case object CreateInstallsTable extends CreateTableStatement("installs") {
  override val sql = s"""
    create table $tableName (
      id uuid primary key,
      user_id uuid not null,
      device_id uuid not null,
      device_info character varying(256)[] not null,
      client character varying(256) not null,
      occurred timestamp not null
    ) with (oids=false);

    create index ${tableName}_user_id_idx on $tableName using btree (user_id);
  """
}
