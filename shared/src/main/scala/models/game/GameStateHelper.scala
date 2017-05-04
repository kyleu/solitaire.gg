package models.game

import java.util.UUID

import models.card.Card
import models.{CardHidden, CardRevealed}

trait GameStateHelper { this: GameState =>
  def addCard(card: Card, pile: String, reveal: Boolean = false): Unit = {
    this.cardsById(card.id) = card
    this.pilesById.get(pile) match {
      case Some(p) => p.addCard(card)
      case None =>
        val msg = s"Cannot find pile [$pile] in known piles [${this.pilesById.keys.toList.sorted.mkString(", ")}]."
        throw new IllegalStateException(msg)
    }
    if (reveal) {
      revealCardToAll(card)
    }
  }

  def addCards(cards: Seq[Card], pile: String, reveal: Boolean = false) = cards.foreach(c => addCard(c, pile, reveal))
  def addCardsFromDeck(numCards: Int, pile: String, reveal: Boolean = false, uniqueRanks: Boolean = false) = if (uniqueRanks) {
    addCards(deck.getCardsUniqueRanks(pilesById(pile).cards.map(_.r), numCards, reveal), pile, reveal)
  } else {
    addCards(deck.getCards(numCards, reveal), pile, reveal)
  }

  def revealInPlace(card: Card) = {
    val p = pileSets.flatMap(_.piles).find(_.cards.exists(_.id == card.id)).getOrElse(throw new IllegalStateException(s"No pile with card [$card]."))
    p.cards(p.cards.indexWhere(_.id == card.id)) = card
    players.map(_.userId).exists(player => revealCardToPlayer(card, player))
  }

  def revealCardToAll(card: Card) = if (playerKnownIds.keys.exists(p => revealCardToPlayer(card, p))) {
    Seq(CardRevealed(card))
  } else {
    Nil
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

  def hideCardFromAll(card: Card) = if (playerKnownIds.keys.exists(p => hideCardFromPlayer(card, p))) {
    Seq(CardHidden(card.id))
  } else {
    Nil
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
}
