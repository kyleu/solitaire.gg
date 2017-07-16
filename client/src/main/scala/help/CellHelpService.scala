package help

import models.rules.CellRules
import util.Messages

object CellHelpService {
  def cell(rules: CellRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.piles.single.cards.empty", loweredName)
      } else if (rules.initialCards == 1) {
        Messages("help.piles.single.cards.single", loweredName)
      } else {
        Messages("help.piles.single.cards.multiple", loweredName, Messages.numberAsString(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.piles.multiple.cards.empty", Messages.numberAsString(x, properCase = true), loweredName)
        } else {
          if (rules.initialCards == 1) {
            Messages("help.piles.multiple.cards.single.each", Messages.numberAsString(x, properCase = true), loweredName)
          } else {
            Messages(
              "help.piles.multiple.cards.multiple.each",
              Messages.numberAsString(x, properCase = true),
              loweredName,
              Messages.numberAsString(rules.initialCards)
            )
          }
        }
    }
    ret += piles

    rules.name -> ret.toIndexedSeq
  }
}
