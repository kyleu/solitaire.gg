package help

import models.rules._
import utils.Messages

object FoundationPilesHelpService {
  def piles(rules: FoundationRules) = {
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
        Messages("help.piles.multiple.cards.empty", Messages.numberAsString(x, properCase = true), loweredName)
      } else if (rules.initialCards % rules.numPiles == 0) {
        if (rules.initialCards / rules.numPiles == 1) {
          Messages("help.piles.multiple.cards.single.each", Messages.numberAsString(x, properCase = true), loweredName)
        } else {
          val init = Messages.numberAsString(rules.initialCards / rules.numPiles)
          Messages("help.piles.multiple.cards.multiple.each", Messages.numberAsString(x, properCase = true), loweredName, init)
        }
      } else {
        if (rules.initialCards == 1) {
          Messages("help.piles.multiple.cards.single", Messages.numberAsString(x, properCase = true), loweredName)
        } else {
          Messages(
            "help.piles.multiple.cards.multiple",
            Messages.numberAsString(x, properCase = true),
            loweredName,
            Messages.numberAsString(rules.initialCards)
          )
        }
      }
    }
  }
}
