package models.game

import java.util.UUID

import models.game.pile.PileSet

case class GameState(
    gameId: UUID,
    rules: String,
    maxPlayers: Int,
    seed: Int,
    deck: Deck,
    pileSets: Seq[PileSet],
    layout: String,
    var players: Seq[GamePlayer] = Nil
) extends GameStateHelper {

  protected[this] val playerKnownIds = collection.mutable.HashMap.empty[UUID, collection.mutable.HashSet[UUID]]
  val cardsById = collection.mutable.HashMap[UUID, Card]()

  val piles = pileSets.flatMap(_.piles)
  val pilesById = piles.map(p => p.id -> p).toMap

  def addPlayer(accountId: UUID, name: String) = players.find(_.account == accountId) match {
    case Some(p) =>
    //log.info("Reconnecting to game [" + gameId + "] from account [" + name + ": " + accountId + "]")
    // TODO Reconnect
    case None =>
      //log.info("Adding player [" + name + ": " + accountId + "] to game [" + gameId + "].")
      val playerIndex = playerKnownIds.size
      if (playerIndex == maxPlayers) {
        throw new IllegalArgumentException("Too many players.")
      }
      players = players :+ GamePlayer(accountId, name)
      playerKnownIds(accountId) = collection.mutable.HashSet.empty
  }

  def getCard(id: UUID) = piles.flatMap(_.cards.find(_.id == id)).headOption.getOrElse(throw new IllegalStateException("Invalid card [" + id + "]."))

  def view(accountId: UUID) = {
    val knownCards = playerKnownIds(accountId)
    this.copy(
      deck = deck.copy(cards = deck.cards.map(c => if (knownCards.contains(c.id)) { c } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) })),
      pileSets = pileSets.map { ps =>
        new PileSet(ps.behavior, ps.piles.map(p => p.copy(cards = p.cards.map { c =>
          if (knownCards.contains(c.id)) { c } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) }
        })), visible = ps.visible)
      }
    )
  }

  def toStrings = Seq(
    "Game ID: " + gameId, "Rules: " + rules, "Seed: " + seed,
    "Players: " + players.map(x => x.account.toString + " (" + x.name + ")").mkString(", "),
    "Deck: " + deck.cards.toString, piles.size + " Piles: "
  ) ++ piles.map(p => p.toString)
}
