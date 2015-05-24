package services.help

import models.game.rules.ReserveRules
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object ReserveHelpService {
  def reserve(rules: ReserveRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.reserve.single.cards.empty")
      } else if (rules.initialCards == 1) {
        Messages("help.reserve.single.cards.single")
      } else {
        Messages("help.reserve.single.cards.multiple", NumberUtils.toWords(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.reserve.multiple.cards.empty", NumberUtils.toWords(x, properCase = true))
        } else if (rules.initialCards == 1) {
          Messages("help.reserve.multiple.cards.single", NumberUtils.toWords(x, properCase = true))
        } else {
          Messages("help.reserve.multiple.cards.multiple", NumberUtils.toWords(x, properCase = true), NumberUtils.toWords(rules.initialCards))
        }
    }
    ret += piles

    rules.name -> ret.toSeq
  }
}
