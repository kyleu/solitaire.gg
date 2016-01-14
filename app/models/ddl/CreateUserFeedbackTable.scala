package models.ddl

object CreateUserFeedbackTable extends CreateTableStatement("user_feedback") {
  override def sql: String = s"""
    create table $tableName (
      id uuid not null primary key,
      device_id uuid,
      active_game_id uuid,
      feedback text not null,
      occurred timestamp without time zone not null
    ) with (oids=false);

    alter table $tableName add constraint ${tableName}_users_fk foreign key (user_id) references users (id) on update no action on delete no action;
    create index ${tableName}_users_idx on $tableName using btree (user_id);
  """
}
