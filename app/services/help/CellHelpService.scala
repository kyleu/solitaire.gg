package services.help

import models.game.rules.CellRules
import play.api.i18n.{Lang, Messages}
import utils.NumberUtils

object CellHelpService {
  def cell(rules: CellRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.cell.single.cards.empty")
      } else if (rules.initialCards == 1) {
        Messages("help.cell.single.cards.single")
      } else {
        Messages("help.cell.single.cards.multiple", NumberUtils.toWords(rules.initialCards))
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.cell.multiple.cards.empty", NumberUtils.toWords(x, properCase = true))
        } else {
          if (rules.initialCards == 1) {
            Messages("help.cell.multiple.cards.single", NumberUtils.toWords(x, properCase = true))
          } else {
            Messages("help.cell.multiple.cards.multiple", NumberUtils.toWords(x, properCase = true), NumberUtils.toWords(rules.initialCards))
          }
        }
    }
    ret += piles

    rules.name -> ret.toSeq
  }
}
