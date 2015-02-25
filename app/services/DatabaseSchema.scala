package services

import com.simple.jdub.{Statement, Database}

object DatabaseSchema {
  def create(d: Database) = {

  }

  def destroy(d: Database) = d.transaction { t =>
    t.execute(DropTable("account"))
    t.execute(DropTable("game"))
  }

  private case class DropTable(name: String) extends Statement {
    override val sql= "drop table if exists ?"
    override val values = Seq(name)
  }
}
