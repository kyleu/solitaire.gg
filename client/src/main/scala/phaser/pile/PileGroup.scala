package phaser.pile

import com.definitelyscala.phaser.{Group, Pointer, Sprite}
import models.pile.options.PileOptions
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.CardSprite

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(val phaser: PhaserGame, val id: String, val pileSet: PileSet, val pileSetIndex: Int, val options: PileOptions) extends Group {
  var cards: Array[CardSprite] = Array.empty

  val empty = {
    val ret = new Sprite(game, 0, 0, "empty-piles", 0)
    ret.inputEnabled = true
    ret.events.onInputUp.add((e: Any, p: Pointer) => {
      if (p.button.toString.toInt == 0) {
        //if(canSelectPile(this)) {
        //game.sendMove({'moveType': 'select-pile', sourcePile: this.id, auto: false});
        //}
      }
    }, this, 0.0)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    add(ret)
    ret
  }

  var intersectWidth = empty.width
  var intersectHeight = empty.height

  def addCard(card: CardSprite, cardPileIndex: Option[Int]) = {
    card.pile = this
    card.pileIndex = cardPileIndex.getOrElse(cards.length)
    cards(card.pileIndex) = card

    PileLayout.cardAdded(this, card)

    if (phaser.initialized) {
      phaser.getPlaymat.resizer.refreshLayout()
    } else {
      phaser.getPlaymat.add(card)
    }
  }
}
