package services

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models.game.variants.GameVariant
import models._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import utils.Logging

import scala.util.{Failure, Success, Try}

object TestService extends Logging {
  case class TestResult(content: String, children: Seq[TestResult] = Nil) {
    def toString(indentionLevel: Int): String = {
      (0 to indentionLevel).map(x => "  ").mkString + content + "\n" + children.map(_.toString(indentionLevel + 1)).mkString
    }
  }

  private def runTest(f: () => TestResult) = {
    val startMs = System.currentTimeMillis()
    val ret = Try {
      f()
    }
    ret match {
      case Success(result) =>
        val msg = "Test [" + result.content + "] completed successfully in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.debug(msg)
        result
      case Failure(ex) =>
        val msg = "Test failed with [" + ex.getClass.getSimpleName + "] in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.warn(msg, ex)
        TestResult(msg, Seq(TestResult(ex.getMessage)))
    }
  }

  def testAll() = TestResult("Running all tests.", Seq(
    testAccount(),
    testVariants()
  ))

  def testAccount() = runTest { () =>
    val testUser = "test-account"
    var account: Option[Account] = None
    val results = Seq(
      {
        account = Some(AccountService.createAccount(testUser))
        TestResult("Account [" + testUser + "] created.")
      },
      {
        AccountService.updateAccountName(account.get.id, "test-account-new-name")
        TestResult("Account [" + testUser + "] updated with new name.")
      },
      {
        AccountService.removeAccount(account.get.id)
        TestResult("Account [" + account.get.id + "] removed.")
      }
    )
    TestResult("account", results)
  }

  def testVariants() = runTest(() => TestResult("all-variants", GameVariant.all.map { v =>
    testVariant(v.key, verbose = false)
  }))

  def testVariant(variant: String, verbose: Boolean = true) = runTest { () =>
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    val results = Seq({
      conn ! StartGame(variant)
      val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].", if(verbose) { gameJoined.state.toStrings.map(x => TestResult(x)) } else { Nil })
    }, {
      conn ! GetPossibleMoves
      val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
      TestResult("Received [" + moves.moves.size + "] possible moves.", if(verbose) { moves.moves.map(x => TestResult(x.toString)) } else { Nil })
    })
    conn ! PoisonPill
    TestResult(variant, results)
  }

  def testKnownGame() = runTest { () =>
    val variant = "klondike"
    val seed = 5
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    var moves = Seq.empty[PossibleMove]
    val results = Seq({
      conn ! StartGame("klondike", Some(seed))
      val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].", gameJoined.state.toStrings.map(x => TestResult(x)))
    }, {
      conn ! GetPossibleMoves
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Received [" + moves.size + "] possible moves.", moves.map( x => TestResult(x.toString)))
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-1").get.cards, "tableau-1", "tableau-6")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[CardMoved])
      println(cardMoved)
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [" + action + "] with result [" + cardMoved + "], received [" + moves.size + "] possible moves.", moves.map( x => TestResult(x.toString)))
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-6").get.cards, "tableau-6", "tableau-4")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[MessageSet])
      println(cardMoved)
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [" + action + "] with result [" + cardMoved + "], received [" + moves.size + "] possible moves.", moves.map( x => TestResult(x.toString)))
    })
    conn ! PoisonPill
    TestResult("known-game", results)
  }
}
