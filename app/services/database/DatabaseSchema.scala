package services.database

import models.queries.DdlQueries.{DropTable, DoesTableExist, CreateGameTable, CreateAccountTable}

object DatabaseSchema {
  def update() = DatabaseConnection.transaction { t =>
    if(t.query(DoesTableExist("accounts"))) {
      t.execute(CreateAccountTable)
    }
    if(t.query(DoesTableExist("games"))) {
      t.execute(CreateGameTable)
    }
  }

  def destroy() = DatabaseConnection.transaction { t =>
    t.execute(DropTable("account"))
    t.execute(DropTable("game"))
    Unit
  }
}
