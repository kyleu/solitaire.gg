package benchmarking

import java.util.UUID

import models.rules.GameRulesSet
import models.rules.moves.InitialMoves

import org.openjdk.jmh.annotations

import scala.util.Random

class GameBenchmarks {
  @annotations.Benchmark
  def games() = {
    val id = UUID.randomUUID
    val rules = "klondike"

    val gr = GameRulesSet.allByIdWithAliases(rules)

    val gs = gr.newGame(id, Random.nextInt(), rules)

    gs.addPlayer(UUID.randomUUID, "Offline Player", autoFlipOption = true)

    InitialMoves.performInitialMoves(gr, gs)
  }
}
