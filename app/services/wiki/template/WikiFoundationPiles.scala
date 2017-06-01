package services.wiki.template

import models.rules._

import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiFoundationPiles {
  def piles(rules: FoundationRules) = {
    val loweredName = rules.name.toLowerCase

    rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        messages("help.piles.single.cards.empty", loweredName)
      } else if (rules.initialCards == 1) {
        messages("help.piles.single.cards.single", loweredName)
      } else {
        messages("help.piles.single.cards.multiple", loweredName, rules.initialCards)
      }
      case x => if (rules.initialCards == 0) {
        messages("help.piles.multiple.cards.empty", WikiService.numberAsString(x, properCase = true), loweredName)
      } else if (rules.initialCards % rules.numPiles == 0) {
        if (rules.initialCards / rules.numPiles == 1) {
          messages("help.piles.multiple.cards.single.each", WikiService.numberAsString(x, properCase = true), loweredName)
        } else {
          val init = WikiService.numberAsString(rules.initialCards / rules.numPiles)
          messages("help.piles.multiple.cards.multiple.each", WikiService.numberAsString(x, properCase = true), loweredName, init)
        }
      } else {
        if (rules.initialCards == 1) {
          messages("help.piles.multiple.cards.single", WikiService.numberAsString(x, properCase = true), loweredName)
        } else {
          messages(
            "help.piles.multiple.cards.multiple",
            WikiService.numberAsString(x, properCase = true),
            loweredName,
            WikiService.numberAsString(rules.initialCards)
          )
        }
      }
    }
  }
}
