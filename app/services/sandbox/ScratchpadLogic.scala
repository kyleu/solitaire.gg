package services.sandbox

import models.database.Statement
import models.ddl.CreateGameSeedsTable
import services.database.Database
import utils.Application
import utils.FutureUtils.defaultContext

import scala.concurrent.Future

trait ScratchpadLogic {
  def call(ctx: Application) = {
    //    val backup = Database.execute(new Statement {
    //      override def sql = "select * into table game_seeds_old from game_seeds"
    //    })

    val backup = Database.execute(new Statement {
      override def sql = "select * into table game_seeds from game_seeds_backup"
    })

    val dropOriginal = backup.flatMap { _ =>
      Database.execute(new Statement {
        override def sql = "drop table game_seeds"
      })
    }

    val createNew = dropOriginal.flatMap { _ =>
      CreateGameSeedsTable.statements.foldLeft(Future.successful(Unit)) { (x, y) =>
        x.flatMap { _ =>
          Database.execute(y).map(_ => Unit)
        }
      }
    }

    val migrateData = createNew.flatMap { _ =>
      Database.execute(new Statement {
        override def sql = """
          insert into game_seeds (
            rules, seed, games, wins, moves, first_player, first_moves, first_elapsed_ms, first_occurred
          ) (
            select rules, seed, games, wins, moves, player, moves, elapsed_ms, completed from game_seeds_old
          )
        """
      })
    }

    migrateData.map { result =>
      "OK: " + result
    }
  }
}
