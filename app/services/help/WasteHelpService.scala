package services.help

import models.game.rules.{ WastePlayableCards, WasteRules }
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object WasteHelpService {
  def waste(rules: WasteRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => Messages("help.piles.single", rules.name.toLowerCase)
      case x => Messages("help.piles.multiple", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += Messages("help.waste.playable.cards.all")
      case WastePlayableCards.TopCardOnly => ret += Messages("help.waste.playable.cards.top")
    }

    rules.name -> ret
  }
}
