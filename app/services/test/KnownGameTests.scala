package services.test

import java.util.UUID

import akka.actor.{ ActorRef, PoisonPill }
import akka.testkit.TestProbe
import models._
import models.test.{ Tree, Test }
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current

class KnownGameTests {
  val rules = "klondike"
  val seed = 5
  implicit val system = Akka.system
  val testProbe = TestProbe()
  val userId = UUID.randomUUID
  var moves = Seq.empty[PossibleMove]
  var conn: ActorRef = _
  var activeGameId: UUID = _

  private[this] def connect() = Test("connect", () => {
    conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, userId, "test-user", testProbe.ref))
  })

  private[this] def start() = Test("start", () => {
    conn ! StartGame("klondike", Some(seed))
    val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
    activeGameId = gameJoined.id
    s"Started [$rules] game [$activeGameId]."
  })

  private[this] def possibleMoves() = Test("possible-moves", () => {
    conn ! GetPossibleMoves
    moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
    s"Started [$rules] game [$activeGameId]."
  })

  private[this] def moveCardsA() = Test("move-cards-a", () => {
    val action = MoveCards(moves.find(_.sourcePile == "tableau-1").getOrElse(throw new IllegalStateException()).cards, "tableau-1", "tableau-6")
    conn ! action
    val cardMoved = testProbe.expectMsgClass(classOf[CardsMoved])
    moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
    s"Performed [MoveCards], received [${moves.size}] possible moves."
  })

  private[this] def moveCardsB() = Test("move-cards-b", () => {
    val action = MoveCards(moves.find(_.sourcePile == "tableau-6").getOrElse(throw new IllegalStateException()).cards, "tableau-6", "tableau-4")
    conn ! action
    val cardMoved = testProbe.expectMsgClass(classOf[CardsMoved])
    moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
    s"Performed [MoveCards], received [${moves.size}] possible moves."
  })

  private[this] def disconnect() = Test("disconnect", () => {
    conn ! PoisonPill
  })

  val all = Tree(Test("known-game"), Seq(
    connect().toTree,
    start().toTree,
    possibleMoves().toTree,
    moveCardsA().toTree,
    moveCardsB().toTree,
    disconnect().toTree
  ))
}
