package services.test

import models.GameMessage
import models.game.variants._

object SolverTests {
  val solvableSeeds = Map(
    Canfield.key -> 100,
    FreeCell.key -> 100,
    Golf.key -> 100,
    KlondikeDrawThree.key -> 11,
    KlondikeDrawOne.key -> 11,
    Nestor.key -> 100,
    Pyramid.key -> 2,
    Sandbox.key -> 1,
    Spider.key -> 50,
    TrustyTwelve.key -> 3
  )
}

trait SolverTests { this: TestService =>
  def testSolvers() = runTest { () =>
    TestResult("solver", GameVariant.all.map(v => testSolver(v.key, verbose = false)))
  }

  def testSolver(variant: String, verbose: Boolean) = runTest { () =>
    val seed = SolverTests.solvableSeeds(variant)
    runSolver(variant, seed, verbose)
  }

  private[this] def runSolver(variant: String, seed: Int, verbose: Boolean): TestResult = {
    val solver = GameSolver(variant, 0, Some(seed))
    val maxMoves = 2000
    val movesPerformed = collection.mutable.ArrayBuffer.empty[GameMessage]
    while (!solver.gameWon && movesPerformed.size < maxMoves) {
      val move = solver.performMove()
      movesPerformed += move
    }

    val children = if (verbose) {
      movesPerformed.map(m => TestResult("Performed [" + m.toString + "]."))
    } else {
      Nil
    }

    if (solver.gameWon) {
      val msg = "Won game [" + variant + "] with seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      TestResult(msg, children)
    } else {
      val msg = "Unable to find a solution for [" + variant + "] seed [" + seed + "] in [" + movesPerformed.size + "] moves."
      TestResult(msg, children)
    }
  }
}
