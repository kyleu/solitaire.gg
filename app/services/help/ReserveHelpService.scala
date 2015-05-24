package services.help

import models.game.rules.ReserveRules
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object ReserveHelpService {
  def reserve(rules: ReserveRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.piles.single.cards.empty", rules.name.toLowerCase)
      } else if (rules.initialCards == 1) {
        Messages("help.piles.single.cards.single", rules.name.toLowerCase)
      } else {
        Messages("help.piles.single.cards.multiple", rules.name.toLowerCase, NumberUtils.toWords(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.piles.multiple.cards.empty", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
        } else if (rules.initialCards == 1) {
          Messages("help.piles.multiple.cards.single.each", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
        } else {
          Messages(
            "help.piles.multiple.cards.multiple.each",
            NumberUtils.toWords(x, properCase = true),
            rules.name.toLowerCase,
            NumberUtils.toWords(rules.initialCards)
          )
        }
    }
    ret += piles

    rules.name -> ret.toSeq
  }
}
