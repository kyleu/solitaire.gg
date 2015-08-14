package models.database.queries.ddl

import models.database.Statement

case object CreateUserStatisticsTable extends Statement {
  override val sql = """
    create table user_statistics (
      id uuid not null primary key,
      joined timestamp not null default now(),
      wins int not null default 0,
      losses int not null default 0,
      total_duration_ms bigint not null default 0,
      total_moves integer not null default 0,
      total_undos integer not null default 0,
      total_redos integer not null default 0,
      last_win timestamp default null,
      last_loss timestamp default null,
      current_win_streak integer not null default 0,
      max_win_streak integer not null default 0,
      current_loss_streak integer not null default 0,
      max_loss_streak integer not null default 0
    ) with (oids=false);

    alter table user_statistics add constraint user_statistics_users_fk foreign key (id) references users (id) on update no action on delete no action;
  """
}
