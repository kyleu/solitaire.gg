package services.test

import models.test.{Test, Tree}

object AllTests {
  val all = Tree(Test("all"), Seq(CardTests.all, PokerTests.all, RulesTests.all))
}
