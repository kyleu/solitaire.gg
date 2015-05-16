package models.database.queries.ddl

import models.database.Statement

case object CreateRequestTable extends Statement {
  override val sql = """
    create table requests (
      id uuid primary key,
      account_id uuid,

      auth_provider charater varying(64),
      auth_key text,

      cookie text,
      host text,
      referrer character text,
      user_agent text,

      started timestamp not null,
      finished timestamp not null,
      completed timestamp
    ) with (
      oids=false
    );

    create index games_player_idx on games using btree (player);
  """
}
