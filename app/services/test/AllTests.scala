package services.test

import akka.actor.ActorRef
import models.test.{ Test, Tree }

class AllTests(supervisor: ActorRef) {
  val all = Tree(Test("all"), Seq(
    new CardTests().all,
    new KnownGameTests(supervisor).all,
    new PokerTests().all,
    new VariantTests(supervisor).all,
    new SolverTests(supervisor).all,
    new RulesTests().all
  ))
}
