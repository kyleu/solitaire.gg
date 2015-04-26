package parser.politaire

import models.game.rules.{ PyramidFillEmptyWith, PyramidType, PyramidFaceDownCards, PyramidRules }
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
        emptyFilledWith = getInt(prefix + "f") match {
          case 0 => PyramidFillEmptyWith.Aces
          case 1 => PyramidFillEmptyWith.Kings
          case 2 => PyramidFillEmptyWith.KingsUntilStockEmpty
          case 4 => PyramidFillEmptyWith.None
          case 5 => PyramidFillEmptyWith.None
          case 7 => PyramidFillEmptyWith.Aces
          case 9 => PyramidFillEmptyWith.KingsOrAces
          case 8 => PyramidFillEmptyWith.Sevens
        },
        mayMoveToEmptyFrom = PolitaireLookup.parseBitmask("P0o", getInt(prefix + "fo"))

      )
    }.toSeq
    pyramids
  }
}
