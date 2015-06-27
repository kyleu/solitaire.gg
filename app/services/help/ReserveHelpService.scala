package services.help

import models.rules.ReserveRules
import play.api.i18n.Messages
import utils.NumberUtils

object ReserveHelpService {
  def reserve(rules: ReserveRules)(implicit messages: Messages) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.piles.single.cards.empty", loweredName)
      } else if (rules.initialCards == 1) {
        Messages("help.piles.single.cards.single", loweredName)
      } else {
        Messages("help.piles.single.cards.multiple", loweredName, NumberUtils.toWords(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.piles.multiple.cards.empty", NumberUtils.toWords(x, properCase = true), loweredName)
        } else if (rules.initialCards == 1) {
          Messages("help.piles.multiple.cards.single.each", NumberUtils.toWords(x, properCase = true), loweredName)
        } else {
          Messages(
            "help.piles.multiple.cards.multiple.each",
            NumberUtils.toWords(x, properCase = true),
            loweredName,
            NumberUtils.toWords(rules.initialCards)
          )
        }
    }
    ret += piles

    rules.name -> ret.toSeq
  }
}
