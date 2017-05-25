package phaser.gameplay

import phaser.card.CardSprite
import phaser.pile.PileGroup
import phaser.playmat.Playmat

class HighlightService(playmat: Playmat) {
  private[this] var highlightedPiles = Seq.empty[PileGroup]
  private[this] var highlightedCards = Seq.empty[CardSprite]

  def highlight(piles: Seq[String] = Nil, cards: Seq[Int] = Nil) = {
    highlightedPiles.foreach(clearPileGroup)
    highlightedPiles = piles.map { pile =>
      val pg = playmat.getPileGroup(pile)
      highlightPileGroup(pg)
      pg
    }

    highlightedCards.foreach(clearCardSprite)
    highlightedCards = cards.map { card =>
      val cs = playmat.getCardSprite(card)
      highlightCardSprite(cs)
      cs
    }
  }

  private[this] def highlightPileGroup(pg: PileGroup) = pg.empty.tint = Math.random() * 0xffffff
  private[this] def clearPileGroup(pg: PileGroup) = pg.empty.tint = 0
  private[this] def highlightCardSprite(cs: CardSprite) = cs.tint = Math.random() * 0xffffff
  private[this] def clearCardSprite(cs: CardSprite) = cs.tint = 0
}
