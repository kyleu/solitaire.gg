package services.test

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models.game.rules.GameRulesSet
import models.test.{ Test, Tree }
import models.{ GetPossibleMoves, PossibleMoves, GameJoined, StartGame }
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current

class VariantTests {
  val all = Tree(Test("variant"), GameRulesSet.completed.map(x => testVariant(x).toTree))

  def testVariant(rules: String) = Test("variant-" + rules, () => {
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    var msg = "Unknown Result"
    conn ! StartGame(rules)
    val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
    conn ! GetPossibleMoves
    val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
    conn ! PoisonPill
    "Received [" + moves.moves.size + "] possible moves."
  })
}
