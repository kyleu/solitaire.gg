package models.database.queries.ddl

import models.database.Statement

case object CreateUserTable extends Statement {
  override val sql = DdlQueries.trim("""
    create table users (
      id uuid primary key,
      login_infos text[] not null,
      username character varying(256),
      email character varying(256),
      avatar_url character varying(512),
      first_name character varying(512),
      last_name character varying(512),
      full_name character varying(512),
      gender character varying(512),
      roles character varying(64)[] not null,
      created timestamp not null
    ) with (
      oids=false
    );

    create index users_login_infos_idx on users using gin (login_infos);
    create index users_username_idx on users using btree (username collate pg_catalog."default");
    create index users_email_idx on users using btree (email collate pg_catalog."default");
    create index users_roles_idx on users using gin (roles);
  """)
}
