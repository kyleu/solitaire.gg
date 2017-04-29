package models.game

import java.util.UUID

import models.card.Card
import models.{CardHidden, CardRevealed, PossibleMove}

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

  def possibleMoves() = {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val awesomeMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val boringMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    piles.foreach { source =>
      val sourceBehavior = source.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
      source.cards.zipWithIndex.foreach { c =>
        val cards = source.cards.drop(c._2)
        val remainingCards = source.cards.take(c._2)
        if (source.canDragFrom(cards, this)) {
          piles.filter(p => p.id != source.id).foreach { target =>
            val targetBehavior = target.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
            if (target.canDragTo(source, cards, this)) {
              val move = PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
              if (sourceBehavior == "tableau" && targetBehavior == "tableau" && remainingCards.isEmpty && target.cards.isEmpty) {
                boringMoves += move
              } else if (targetBehavior == "foundation" && sourceBehavior != "foundation") {
                awesomeMoves += move
              } else {
                ret += move
              }
            }
          }
        }
        if (source.canSelectCard(c._1, this)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
      }
      if (source.canSelectPile(this)) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
    }
    (awesomeMoves ++ ret ++ boringMoves).toIndexedSeq
  }
}
