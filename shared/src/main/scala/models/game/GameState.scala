package models.game

import java.util.UUID

import models.ResponseMessage
import models.card.{Card, Deck, Rank, Suit}
import models.pile.set.PileSet

case class GameState(
    gameId: UUID,
    rules: String,
    rulesTitle: String,
    maxPlayers: Int,
    seed: Int,
    deck: Deck,
    pileSets: IndexedSeq[PileSet],
    layout: String,
    var stockCounter: Int = 0
) extends GameStateHelper {
  var players = IndexedSeq.empty[GamePlayer]

  protected[this] val playerKnownIds = collection.mutable.HashMap.empty[UUID, collection.mutable.HashSet[Int]]
  val cardsById = collection.mutable.HashMap[Int, Card]()

  val piles = pileSets.flatMap(_.piles)
  val pilesById = piles.map(p => p.id -> p).toMap

  def apply(msg: ResponseMessage) = GameStateApply.applyMessage(this, msg)

  def addPlayer(userId: UUID, name: String, autoFlipOption: Boolean) = players.find(_.userId == userId) match {
    case Some(_) =>
      // TODO Reconnect
    case None =>
      val playerIndex = playerKnownIds.size
      if (playerIndex == maxPlayers) {
        throw new IllegalArgumentException("Too many players.")
      }
      players = players :+ GamePlayer(userId, name, autoFlipOption)
      playerKnownIds(userId) = collection.mutable.HashSet.empty
  }

  def refreshCaches() = {
    cardsById.clear()
    pileSets.foreach(_.piles.foreach(_.cards.foreach(c => cardsById(c.id) = c)))
  }

  def setAutoFlipOption(autoFlip: Boolean) = players = players.map(p => p.copy(autoFlipOption = autoFlip))

  def getCard(id: Int) = piles.flatMap(_.cards.find(_.id == id)).headOption.getOrElse(throw new IllegalStateException(s"Invalid card [$id]."))

  def view(userId: UUID) = {
    val knownCards = playerKnownIds(userId)
    val ret = this.copy(
      deck = deck.copy(cards = deck.cards.map(c => if (knownCards.contains(c.id)) { c } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) })),
      pileSets = pileSets.map { ps =>
        PileSet(ps.behavior, ps.piles.map(p => p.copy(cards = p.cards.map { c =>
          if (knownCards.contains(c.id)) { c.copy() } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) }
        })), visible = ps.visible)
      }
    )
    ret.refreshCaches()
    ret
  }

  def calculateScore(moveCount: Int, undoCount: Int, elapsed: Int) = {
    val baseScore = cardsById.size * 50
    val moveDebit = moveCount * 10
    val undoDebit = undoCount * 100
    val timeDebit = elapsed

    val score = baseScore - moveDebit - undoDebit - timeDebit

    Math.max(1, score)
  }

  def toStrings = Seq(
    s"Game ID: $gameId", s"Rules: $rules", s"Seed: $seed",
    s"Players: ${players.map(x => s"${x.userId} (${x.name})").mkString(", ")}",
    s"Deck: ${deck.cards}", s"${piles.size} Piles: "
  ) ++ piles.map(p => p.toString)
}
