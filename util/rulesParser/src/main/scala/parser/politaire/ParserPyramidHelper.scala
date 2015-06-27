package parser.politaire

import models.rules.{ FillEmptyWith, PyramidType, PyramidFaceDownCards, PyramidRules }
import parser.politaire.lookup.PolitaireLookup

trait ParserPyramidHelper { this: GameRulesParser =>
  protected[this] def getPyramids = {
    val pyramidCount = getInt("Pn")
    val pyramids = (0 to pyramidCount - 1).map { i =>
      val prefix = "P" + i

      var height = getInt(prefix + "size")
      var wtf = getString(prefix + "ds")
      if(height == 7 && wtf.nonEmpty) {
        height = wtf.length + 1
      }

      PyramidRules(
        name = getString(prefix + "Nm"),
        pyramidType = getInt(prefix + "type") match {
          case 1 => PyramidType.Standard
          case 2 => PyramidType.Standard
          case 3 => PyramidType.Standard
        },
        height = height,
        cardsFaceDown = getInt(prefix + "df") match {
          case 100 => PyramidFaceDownCards.AllButLastRow
          case 101 => PyramidFaceDownCards.EvenNumbered
          case 102 => PyramidFaceDownCards.OddNumbered
          case x => PyramidFaceDownCards.Count(x)
        },

        suitMatchRuleForBuilding = getSuitMatchRule(getInt(prefix + "s")),
        rankMatchRuleForBuilding = getRankMatchRule(getInt(prefix + "r")),
        wrap = getBoolean(prefix + "w"),
        suitMatchRuleForMovingStacks = getSuitMatchRule(getInt(prefix + "ts")),
        rankMatchRuleForMovingStacks = getRankMatchRule(getInt(prefix + "tr")),

        mayMoveToNonEmptyFrom = PolitaireLookup.parseBitmask("P0o", getInt(prefix + "o")),
        emptyFilledWith = getFillEmptyWith(getInt(prefix + "f")),
        mayMoveToEmptyFrom = PolitaireLookup.parseBitmask("P0o", getInt(prefix + "fo"))
      )
    }.toSeq
    pyramids
  }
}
