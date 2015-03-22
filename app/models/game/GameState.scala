package models.game

import java.util.UUID

import models.CardRevealed
import models.game.pile.Pile

case class GameState(
  gameId: UUID,
  variant: String,
  seed: Int,
  var players: Seq[GamePlayer],
  deck: Deck,
  piles: Seq[Pile],
  layouts: Seq[Layout]
) {
  val cardsById = collection.mutable.HashMap[UUID, Card]()
  val pilesById = piles.map(p => p.id -> p).toMap
  val playerKnownCardIds = collection.mutable.HashMap.empty[UUID, collection.mutable.HashSet[UUID]]

  for(p <- players) {
    addPlayer(p.account, p.name)
  }

  def addPlayer(accountId: UUID, name: String) = {
    players = players :+ GamePlayer(accountId, name)
    playerKnownCardIds(accountId) = collection.mutable.HashSet.empty
  }

  def addCard(card: Card, pile: String, reveal: Boolean = false) {
    this.cardsById(card.id) = card
    this.pilesById(pile).addCard(card)
    if(reveal) {
      revealCardToAll(card)
    }
  }

  def addCards(cards: Seq[Card], pile: String, reveal: Boolean = false) = cards.foreach { c =>
    addCard(c, pile, reveal)
  }

  def revealCardToAll(card: Card) = playerKnownCardIds.keys.flatMap(p => revealCardToPlayer(card, p)).toList

  def revealCardToPlayer(card: Card, player: UUID) = {
    val existing = playerKnownCardIds(player)
    if(!existing.contains(card.id)) {
      existing += card.id
      Some(CardRevealed(card))
    } else {
      None
    }
  }

  def view(accountId: UUID) = {
    val knownCards = playerKnownCardIds(accountId)
    this.copy(
      deck = deck.copy(cards = deck.cards.map( c => if(knownCards.contains(c.id)) { c } else { c.copy( r = Rank.Unknown, s = Suit.Unknown ) })),
      piles = piles.map( p => p.copy( cards = p.cards.map( c => if(knownCards.contains(c.id)) { c } else { c.copy( r = Rank.Unknown, s = Suit.Unknown ) })) )
    )
  }

  def toStrings = Seq(
    "Game ID: " + gameId,
    "Variant: " + variant,
    "Seed: " + seed,
    "Players: " + players.map(x => x.account + " (" + x.name + ")").mkString(", "),
    "Deck: " + deck.cards.toString,
    piles.size + " Piles: "
  ) ++ piles.map( p => p.toString)
}
