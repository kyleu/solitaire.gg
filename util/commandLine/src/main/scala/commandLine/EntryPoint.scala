package commandLine

import java.util.UUID

import models.rules.GameRulesSet
import models.rules.moves.InitialMoves

import scala.util.Random

object EntryPoint {
  def main(args: Array[String]): Unit = {
    GameRulesSet.all.foreach { gr =>
      val gs = gr.newGame(UUID.randomUUID(), Random.nextInt(), gr.id)
      gs.addPlayer(UUID.randomUUID, "Offline Player", autoFlipOption = true)
      InitialMoves.performInitialMoves(gr, gs)
      println(gs.rulesTitle)
    }
  }
}
