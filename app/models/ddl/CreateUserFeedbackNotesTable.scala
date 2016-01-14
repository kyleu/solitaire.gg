package models.ddl

object CreateUserFeedbackNotesTable extends CreateTableStatement("user_feedback_notes") {
  override def sql: String = s"""
    create table $tableName (
      id uuid not null primary key,
      feedback_id uuid not null,
      author_id uuid not null,
      content text not null,
      occurred timestamp without time zone not null
    ) with (oids=false);

    alter table $tableName add constraint ${tableName}_${CreateUserFeedbackTable.tableName}_fk
      foreign key (feedback_id) references ${CreateUserFeedbackTable.tableName} (id) on update no action on delete no action;

    create index ${tableName}_feedback_id_idx on $tableName using btree (feedback_id);
  """
}
