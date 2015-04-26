package services.test

import models.GameMessage
import models.game.variants._
import models.test.{ Test, Tree }

object SolverTests {
  val solvableSeeds = Map(
    Canfield.key -> 100,
    FreeCell.key -> 100,
    Golf.key -> 100,
    Gypsy.key -> 100,
    Klondike.key -> 11,
    Nestor.key -> 100,
    Pyramid.key -> 2,
    Sandbox.key -> 1,
    SandboxB.key -> 1,
    Spider.key -> 50,
    TrustyTwelve.key -> 3,
    Yukon.key -> 0
  )
}

class SolverTests {
  val all = Tree(Test("solver"), GameVariant.all.map(x => testSolver(x.key).toTree))

  def testSolver(variant: String) = Test("solver-" + variant, { () =>
    val seed = SolverTests.solvableSeeds(variant)
    runSolver(variant, seed)
  })

  private[this] def runSolver(variant: String, seed: Int) = {
    val solver = GameSolver(variant, 0, Some(seed))
    val maxMoves = 2000
    val movesPerformed = collection.mutable.ArrayBuffer.empty[GameMessage]
    while (!solver.gameWon && movesPerformed.size < maxMoves) {
      val move = solver.performMove()
      movesPerformed += move
    }

    if (solver.gameWon) {
      val msg = "Won game [" + variant + "] with seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      msg
    } else {
      val msg = "Unable to find a solution for [" + variant + "] seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      msg
    }
  }
}
