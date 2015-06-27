package services.help

import models.rules.{ WastePlayableCards, WasteRules }
import play.api.i18n.Messages
import utils.NumberUtils

object WasteHelpService {
  def waste(rules: WasteRules)(implicit messages: Messages) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => Messages("help.piles.single", loweredName)
      case x => Messages("help.piles.multiple", NumberUtils.toWords(x, properCase = true), loweredName)
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += Messages("help.waste.playable.cards.all", loweredName)
      case WastePlayableCards.TopCardOnly => ret += Messages("help.waste.playable.cards.top", loweredName)
    }

    rules.name -> ret
  }
}
