package services.test

import models.GameMessage
import models.rules.GameRulesSet
import models.test.{Test, Tree}

import scala.util.Random

class SolverTests() {
  val all = Tree(Test("solver"), GameRulesSet.all.map(x => testSolver(x.id).toTree))

  def testSolver(rules: String) = Test(s"solver-$rules", { () =>
    val seed = Random.nextInt(1000000)
    runSolver(rules, seed)
  })

  private[this] def runSolver(rules: String, seed: Int) = {
    val solver = GameSolver(rules, 0, seed)
    val movesPerformed = collection.mutable.ArrayBuffer.empty[GameMessage]
    while (!solver.gameWon && movesPerformed.size < GameSolver.moveLimit) {
      val move = solver.performMove()
      movesPerformed += move
    }

    if (solver.gameWon) {
      s"Won game [$rules] with seed [$seed] in [${movesPerformed.size}] moves."
    } else {
      s"Unable to find a solution for [$rules] seed [$seed] in [${movesPerformed.size}] moves."
    }
  }
}
