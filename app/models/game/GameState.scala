package models.game

import java.util.UUID

import models.{ ResponseMessage, CardHidden, CardRevealed }
import models.game.pile.Pile
import utils.Logging

case class GameState(
    gameId: UUID,
    variant: String,
    maxPlayers: Int,
    seed: Int,
    deck: Deck,
    piles: Seq[Pile],
    layouts: Seq[Layout],
    var players: Seq[GamePlayer] = Nil
) extends Logging {

  private[this] val playerKnownIds = collection.mutable.HashMap.empty[UUID, collection.mutable.HashSet[UUID]]
  val cardsById = collection.mutable.HashMap[UUID, Card]()
  val pilesById = piles.map(p => p.id -> p).toMap

  def addPlayer(accountId: UUID, name: String) = {
    players.find(_.account == accountId) match {
      case Some(p) =>
        log.info("Reconnecting to game [" + gameId + "] from account [" + name + ": " + accountId + "]")
      // TODO Reconnect
      case None =>
        log.info("Adding player [" + name + ": " + accountId + "] to game [" + gameId + "].")
        val playerIndex = playerKnownIds.size
        if (playerIndex == maxPlayers) {
          throw new IllegalArgumentException("Too many players.")
        }
        players = players :+ GamePlayer(accountId, name)
        playerKnownIds(accountId) = collection.mutable.HashSet.empty
    }
  }

  def addCard(card: Card, pile: String, reveal: Boolean = false) {
    this.cardsById(card.id) = card
    this.pilesById(pile).addCard(card)
    if (reveal) {
      revealCardToAll(card)
    }
  }

  def addCards(cards: Seq[Card], pile: String, reveal: Boolean = false) = cards.foreach { c =>
    addCard(c, pile, reveal)
  }

  def revealCardToAll(card: Card) = {
    if(playerKnownIds.keys.exists(p => revealCardToPlayer(card, p))) {
      Seq(CardRevealed(card))
    } else {
      Nil
    }
  }

  def revealCardToPlayer(card: Card, player: UUID) = {
    val existing = playerKnownIds(player)
    if (!existing.contains(card.id)) {
      existing += card.id
      true
    } else {
      false
    }
  }

  def hideCardFromAll(card: Card) = {
    if(playerKnownIds.keys.exists(p => hideCardFromPlayer(card, p))) {
      Seq(CardHidden(card.id))
    } else {
      Nil
    }
  }

  def hideCardFromPlayer(card: Card, player: UUID) = {
    val existing = playerKnownIds(player)
    if (existing.contains(card.id)) {
      existing -= card.id
      true
    } else {
      false
    }
  }

  def getCard(id: UUID) = piles.flatMap(_.cards.find(_.id == id)).headOption.getOrElse(throw new IllegalStateException("Invalid card [" + id + "]."))

  def view(accountId: UUID) = {
    val knownCards = playerKnownIds(accountId)
    this.copy(
      deck = deck.copy(cards = deck.cards.map(c => if (knownCards.contains(c.id)) { c } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) })),
      piles = piles.map(p => p.copy(cards = p.cards.map(c => if (knownCards.contains(c.id)) { c } else { c.copy(r = Rank.Unknown, s = Suit.Unknown) })))
    )
  }

  def toStrings = Seq(
    "Game ID: " + gameId,
    "Variant: " + variant,
    "Seed: " + seed,
    "Players: " + players.map(x => x.account.toString + " (" + x.name + ")").mkString(", "),
    "Deck: " + deck.cards.toString,
    piles.size + " Piles: "
  ) ++ piles.map(p => p.toString)
}
