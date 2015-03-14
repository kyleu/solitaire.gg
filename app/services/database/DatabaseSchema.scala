package services.database

import com.simple.jdub.{Database, Statement}

object DatabaseSchema {
  def create(d: Database) {

  }

  def destroy(d: Database) = d.transaction { t =>
    t.execute(DropTable("account"))
    t.execute(DropTable("game"))
    Unit
  }

  private case class DropTable(name: String) extends Statement {
    override val sql= "drop table if exists ?"
    override val values = Seq(name)
  }
}
