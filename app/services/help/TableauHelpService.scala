package services.help

import models.game.rules.{ InitialCards, TableauRules }
import utils.NumberUtils

object TableauHelpService {
  def tableau(rules: TableauRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if(rules.initialCards == InitialCards.Count(0)) {
        "A single empty tableau pile."
      } else {
        "A single tableau pile with " + rules.initialCards + " initial cards."
      }
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if(rules.initialCards == InitialCards.Count(0)) {
          " empty tableau piles."
        } else {
          rules.initialCards match {
            case InitialCards.Count(i) =>
              val plural = if(i == 1) { "" } else { "s" }
              " tableau piles with " + NumberUtils.toWords(i) + " initial card" + plural + " dealt to each."
            case InitialCards.PileIndex =>
              " tableau piles with one card dealt to the first pile, two to the second, and so on."
            case ic => ic.toString
          }
        })
    }
    ret += piles

    rules.name -> ret.toSeq
  }
}
