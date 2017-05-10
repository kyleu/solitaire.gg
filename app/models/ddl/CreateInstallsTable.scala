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

    create index ${tableName}_device_id_idx on $tableName using btree (device_id);

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_fk
      foreign key (user_id) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
