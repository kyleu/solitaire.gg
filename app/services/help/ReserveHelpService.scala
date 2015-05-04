package services.help

import models.game.rules.{ InitialCards, ReserveRules }
import utils.NumberUtils

object ReserveHelpService {
  def reserve(rules: ReserveRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if(rules.initialCards == 0) {
        "A single empty reserve pile."
      } else {
        "A single reserve pile with " + NumberUtils.toWords(rules.initialCards) + " initial cards."
      }
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if(rules.initialCards == 0) {
          " empty reserve piles."
        } else {
          val plural = if(rules.initialCards == 1) { "" } else { "s" }
          " reserve piles with " + NumberUtils.toWords(rules.initialCards) + " initial card" + plural + " dealt to each."
        })
    }
    ret += piles

    rules.name -> ret.toSeq

  }
}
