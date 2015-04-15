package services.test

import models.game.variants._

object SolverTests {
  val solvableSeeds = Map(
    Canfield.key -> 100,
    FreeCell.key -> 100,
    Golf.key -> 100,
    KlondikeDrawThree.key -> 11,
    KlondikeDrawOne.key -> 11,
    Nestor.key -> 100,
    Pyramid.key -> 1,
    Sandbox.key -> 1,
    Spider.key -> 50,
    TrustyTwelve.key -> 3
  )
}

trait SolverTests { this: TestService =>
  def testSolver() = runTest { () =>
    TestResult("solver", GameVariant.all.map { variant =>
      val seed = SolverTests.solvableSeeds(variant.key)
      runTest(() => testVariant(variant.key, seed))
    })
  }

  private[this] def testVariant(variant: String, seed: Int): TestResult = {
    val solver = GameSolver(variant, 0, Some(seed))
    val maxMoves = 2000
    var movesPerformed = 0
    while(!solver.gameWon && movesPerformed < maxMoves) {
      movesPerformed += 1
      solver.performMove()
    }

    if(solver.gameWon) {
      val msg = "Won game [" + variant + "] with seed [" + seed + "] in [" + movesPerformed + "] moves."
      TestResult(msg)
    } else {
      val msg = "Unable to find a solution for [" + variant + "] seed [" + seed + "] in [" + movesPerformed + "] moves."
      TestResult(msg)
    }
  }
}
