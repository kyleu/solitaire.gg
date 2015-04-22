package services.test

import models.game.generated.GameRulesSet
import models.game.rules.GameRules

trait RulesTests { this: TestService =>
  def testAllGameRules() = runTest(() => TestResult("all-rules", GameRulesSet.all.map { gr =>
    testGameRules(gr, verbose = false)
  }))

  def testGameRules(gr: GameRules, verbose: Boolean = true) = runTest { () =>
    val ret = "OK"
    TestResult(gr.id + ": " + ret)
  }

  def getDeck(rules: GameRules) = {

  }

  def getPileGroups(rules: GameRules) = {

  }
}
