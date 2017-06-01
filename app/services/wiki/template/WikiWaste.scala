package services.wiki.template

import models.rules.{WastePlayableCards, WasteRules}
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiWaste {
  def waste(rules: WasteRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => messages("help.piles.single", loweredName)
      case x => messages("help.piles.multiple", WikiService.numberAsString(x, properCase = true), loweredName)
    }
    ret += piles

    rules.playableCards match {
      case WastePlayableCards.All => ret += messages("help.waste.playable.cards.all", loweredName)
      case WastePlayableCards.TopCardOnly => ret += messages("help.waste.playable.cards.top", loweredName)
    }

    rules.name -> ret
  }
}
