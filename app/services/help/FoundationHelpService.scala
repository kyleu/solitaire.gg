package services.help

import models.game.rules.FoundationRules
import utils.NumberUtils

object FoundationHelpService {
  def foundation(rules: FoundationRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => "A single foundation pile " + (if(rules.initialCards == 0) {
        ""
      } else {
        "with " + rules.initialCards + " initial cards."
      })
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if(rules.initialCards == 0) {
          " empty foundation piles."
        } else if(rules.initialCards % rules.numPiles == 0) {
          val plural = if(rules.initialCards / rules.numPiles == 1) { "" } else { "s" }
          " foundation piles with " + NumberUtils.toWords(rules.initialCards / rules.numPiles) + " initial card" + plural + " dealt to each."
        } else {
          " foundation piles with " + rules.initialCards + " initial cards."
        })
    }
    ret += piles


    rules.name -> ret.toSeq
  }
}
