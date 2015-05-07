package services.help

import models.game.rules._
import utils.NumberUtils

object PyramidHelpService {
  def pyramid(rules: PyramidRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val rows = rules.height match {
      case 1 => "a single row"
      case x => NumberUtils.toWords(rules.height) + " rows"
    }

    rules.pyramidType match {
      case PyramidType.Standard => ret += "A standard pyramid with " + rows + " rows (the top row has only one card, two in the next, and so on)."
      case PyramidType.Inverted => ret += "An inverted pyramid with " + rows + " rows (the bottom row has only one card, two in the next, and so on)."
    }

    if (rules.wrapFromKingToAce) {
      ret += "An Ace may be played on a King, continuing the sequence."
    }

    rules.suitMatchRuleForBuilding match {
      case SuitMatchRule.None => ret += "No cards may be built on the pyramid."
      case sb => rules.rankMatchRuleForBuilding match {
        case RankMatchRule.None => ret += "No cards may be built on the pyramid."
        case rb => ret += "Cards that are " + rb.toWords + " and " + sb.toWords + " may be added to these piles."
      }
    }

    rules.suitMatchRuleForMovingStacks match {
      case SuitMatchRule.None => ret += "No cards may be moved from the pyramid."
      case sb => rules.rankMatchRuleForMovingStacks match {
        case RankMatchRule.None => ret += "No cards may be moved from the pyramid."
        case rb => ret += "Sequences of cards that are " + rb.toWords + " and " + sb.toWords + " may be moved from the pyramid."
      }
    }

    ret += rules.emptyFilledWith.toWords

    rules.name -> ret
  }
}
