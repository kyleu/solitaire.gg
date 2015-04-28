package services.test

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models.test.{ Test, Tree }
import models.{ GetPossibleMoves, PossibleMoves, GameJoined, StartGame }
import models.game.variants.GameVariant
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current

class VariantTests {
  val all = Tree(Test("variant"), GameVariant.completed.map(x => testVariant(x).toTree))

  def testVariant(variant: String) = Test("variant-" + variant, () => {
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    var msg = "Unknown Result"
    conn ! StartGame(variant)
    val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
    conn ! GetPossibleMoves
    val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
    conn ! PoisonPill
    "Received [" + moves.moves.size + "] possible moves."
  })
}
