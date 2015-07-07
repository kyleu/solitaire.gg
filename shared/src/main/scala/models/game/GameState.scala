package models.game

import java.util.UUID

import models.pile.set.PileSet

case class GameState(
    gameId: UUID,
    rules: String,
    rulesTitle: String,
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

  def addPlayer(userId: UUID, name: String, autoFlipOption: Boolean) = players.find(_.userId == userId) match {
    case Some(p) =>
    //log.info("Reconnecting to game [" + gameId + "] from user [" + name + ": " + userId + "]")
    // TODO Reconnect
    case None =>
      //log.info("Adding player [" + userId + ": " + name + "] to game [" + gameId + "].")
      val playerIndex = playerKnownIds.size
      if (playerIndex == maxPlayers) {
        throw new IllegalArgumentException("Too many players.")
      }
      players = players :+ GamePlayer(userId, name, autoFlipOption)
      playerKnownIds(userId) = collection.mutable.HashSet.empty
  }

  def getCard(id: UUID) = piles.flatMap(_.cards.find(_.id == id)).headOption.getOrElse(throw new IllegalStateException(s"Invalid card [$id]."))

  def view(userId: UUID) = {
    val knownCards = playerKnownIds(userId)
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
    s"Game ID: $gameId", s"Rules: $rules", s"Seed: $seed",
    s"Players: ${players.map(x => s"${x.userId} (${x.name})").mkString(", ")}",
    s"Deck: ${deck.cards}", s"${piles.size} Piles: "
  ) ++ piles.map(p => p.toString)
}
