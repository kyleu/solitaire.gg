package services.help

import models.game.rules.{ WastePlayableCards, WasteRules }
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object WasteHelpService {
  def waste(rules: WasteRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => Messages("help.waste.piles.single")
      case x => Messages("help.waste.piles.multiple", NumberUtils.toWords(x, properCase = true))
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += Messages("help.waste.playable.cards.all")
      case WastePlayableCards.TopCardOnly => ret += Messages("help.waste.playable.cards.top")
    }

    rules.name -> ret
  }
}
