package services.test

import akka.actor.ActorRef
import models.test.{Test, Tree}

class AllTests() {
  val all = Tree(Test("all"), Seq(
    new CardTests().all,
    new PokerTests().all,
    new RulesTests().all
  ))
}
