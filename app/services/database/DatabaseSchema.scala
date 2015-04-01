package services.database

import models.queries.DdlQueries._

object DatabaseSchema {
  def update() = DatabaseConnection.transaction {
    DatabaseConnection.execute(EnableUuidIndex)
    for (table <- tables) {
      if (!DatabaseConnection.query(DoesTableExist(table._1))) {
        DatabaseConnection.execute(table._2)
      }
    }
  }

  def destroy() = DatabaseConnection.transaction {
    for (table <- tables) {
      DatabaseConnection.execute(DropTable(table._1))
    }
  }
}
