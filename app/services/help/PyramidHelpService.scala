package services.help

import models.game.rules.{ PyramidType, PyramidRules }
import utils.NumberUtils

object PyramidHelpService {
  def pyramid(rules: PyramidRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val rows = rules.height match {
      case 1 => "a single row"
      case x => NumberUtils.toWords(rules.height) + " rows"
    }

    rules.pyramidType match {
      case PyramidType.Standard => ret += "A standard pyramid with " + rows + " (the top row has only one card, two in the next, and so on)."
      case PyramidType.Inverted => ret += "An inverted pyramid with " + rows + " (the bottom row has only one card, two in the next, and so on)."
      case PyramidType.Custom => ret += "A pyramid with a custom layout. It's freaky."
    }

    rules.name -> ret
  }
}
