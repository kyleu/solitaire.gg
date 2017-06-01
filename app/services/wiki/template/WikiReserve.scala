package services.wiki.template

import models.rules.ReserveRules
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiReserve {
  def reserve(rules: ReserveRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        messages("help.piles.single.cards.empty", loweredName)
      } else if (rules.initialCards == 1) {
        messages("help.piles.single.cards.single", loweredName)
      } else {
        messages("help.piles.single.cards.multiple", loweredName, WikiService.numberAsString(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          messages("help.piles.multiple.cards.empty", WikiService.numberAsString(x, properCase = true), loweredName)
        } else if (rules.initialCards == 1) {
          messages("help.piles.multiple.cards.single.each", WikiService.numberAsString(x, properCase = true), loweredName)
        } else {
          messages(
            "help.piles.multiple.cards.multiple.each",
            WikiService.numberAsString(x, properCase = true),
            loweredName,
            WikiService.numberAsString(rules.initialCards)
          )
        }
    }
    ret += piles

    rules.name -> ret
  }
}
