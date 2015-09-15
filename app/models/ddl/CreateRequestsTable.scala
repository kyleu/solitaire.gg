package models.ddl

case object CreateRequestsTable extends CreateTableStatement("requests") {
  override val sql = s"""
    create table $tableName (
      id uuid primary key not null,
      user_id uuid not null,
      auth_provider character varying(64) not null,
      auth_key text not null,
      remote_address character varying(64) not null,

      method character varying(10) not null,
      host text not null,
      secure boolean not null,
      path text not null,
      query_string text,

      lang text,
      cookie text,
      referrer text,
      user_agent text,
      started timestamp not null,
      duration integer not null,
      status integer not null
    ) with (oids=false);

    create index ${tableName}_account_idx on $tableName using btree (user_id);

    alter table $tableName add constraint ${tableName}_${CreateUsersTable.tableName}_fk
      foreign key (user_id) references ${CreateUsersTable.tableName} (id) on update no action on delete no action;
  """
}
