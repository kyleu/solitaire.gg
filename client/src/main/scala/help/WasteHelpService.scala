package help

import models.rules.{WastePlayableCards, WasteRules}
import util.Messages

object WasteHelpService {
  def waste(rules: WasteRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => Messages("help.piles.single", loweredName)
      case x => Messages("help.piles.multiple", Messages.numberAsString(x, properCase = true), loweredName)
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += Messages("help.waste.playable.cards.all", loweredName)
      case WastePlayableCards.TopCardOnly => ret += Messages("help.waste.playable.cards.top", loweredName)
    }

    rules.name -> ret
  }
}
