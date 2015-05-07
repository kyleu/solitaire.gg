package parser.politaire

import models.game.rules.{ FillEmptyWith, PyramidType, PyramidFaceDownCards, PyramidRules }
import parser.politaire.lookup.PolitaireLookup

trait ParserPyramidHelper { this: GameRulesParser =>
  protected[this] def getPyramids = {
    val pyramidCount = getInt("Pn")
    val pyramids = (0 to pyramidCount - 1).map { i =>
      val prefix = "P" + i
      PyramidRules(
        name = getString(prefix + "Nm"),
        pyramidType = getInt(prefix + "type") match {
          case 1 => PyramidType.Standard
          case 2 => PyramidType.Standard
          case 3 => PyramidType.Standard
        },
        height = getInt(prefix + "size"),
        cardsFaceDown = getInt(prefix + "df") match {
          case 100 => PyramidFaceDownCards.AllButLastRow
          case 101 => PyramidFaceDownCards.EvenNumbered
          case 102 => PyramidFaceDownCards.OddNumbered
          case x => PyramidFaceDownCards.Count(x)
        },

        suitMatchRuleForBuilding = getSuitMatchRule(getInt(prefix + "s")),
        rankMatchRuleForBuilding = getRankMatchRule(getInt(prefix + "r")),
        wrapFromKingToAce = getBoolean(prefix + "w"),
        suitMatchRuleForMovingStacks = getSuitMatchRule(getInt(prefix + "ts")),
        rankMatchRuleForMovingStacks = getRankMatchRule(getInt(prefix + "tr")),

        mayMoveToNonEmptyFrom = PolitaireLookup.parseBitmask("P0o", getInt(prefix + "o")),
        emptyFilledWith = FillEmptyWith.fromInt(getInt(prefix + "f")),
        mayMoveToEmptyFrom = PolitaireLookup.parseBitmask("P0o", getInt(prefix + "fo"))

      )
    }.toSeq
    pyramids
  }
}
