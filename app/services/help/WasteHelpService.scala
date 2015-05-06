package services.help

import models.game.rules.{ WastePlayableCards, WasteRules }
import utils.NumberUtils

object WasteHelpService {
  def waste(rules: WasteRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => "A single empty waste pile."
      case x => NumberUtils.toWords(x, properCase = true) + " waste piles."
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += "Any card may be moved from the waste."
      case WastePlayableCards.TopCardOnly => ret += "The top card may be moved from the waste."
    }

    rules.name -> ret
  }
}
