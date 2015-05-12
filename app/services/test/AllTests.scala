package services.test

import models.test.{ Test, Tree }

class AllTests {
  val all = Tree(Test("all"), Seq(
    new CardTests().all,
    new KnownGameTests().all,
    new VariantTests().all,
    new SolverTests().all,
    new RulesTests().all
  ))
}
