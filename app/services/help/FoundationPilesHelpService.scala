package services.help

import models.game.rules._
import play.api.i18n.{Lang, Messages}
import utils.NumberUtils

object FoundationPilesHelpService {
  def piles(rules: FoundationRules)(implicit lang: Lang) = {
    val loweredName = rules.name.toLowerCase

    rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.piles.single.cards.empty", loweredName)
      } else if (rules.initialCards == 1) {
        Messages("help.piles.single.cards.single", loweredName)
      } else {
        Messages("help.piles.single.cards.multiple", loweredName, rules.initialCards)
      }
      case x => if (rules.initialCards == 0) {
        Messages("help.piles.multiple.cards.empty", NumberUtils.toWords(x, properCase = true), loweredName)
      } else if (rules.initialCards % rules.numPiles == 0) {
        if (rules.initialCards / rules.numPiles == 1) {
          Messages("help.piles.multiple.cards.single.each", NumberUtils.toWords(x, properCase = true), loweredName)
        } else {
          val init = NumberUtils.toWords(rules.initialCards / rules.numPiles)
          Messages("help.piles.multiple.cards.multiple.each", NumberUtils.toWords(x, properCase = true), loweredName, init)
        }
      } else {
        if (rules.initialCards == 1) {
          Messages("help.piles.multiple.cards.single", NumberUtils.toWords(x, properCase = true), loweredName)
        } else {
          Messages(
            "help.piles.multiple.cards.multiple",
            NumberUtils.toWords(x, properCase = true),
            loweredName,
            NumberUtils.toWords(rules.initialCards)
          )
        }
      }
    }
  }
}
