package parser.politaire

import models.game._
import models.game.rules._
import parser.politaire.lookup.PolitaireLookup

trait ParserFoundationHelper { this: GameRulesParser =>
  protected[this] def getFoundations(deckOptions: DeckOptions) = {
    val foundationCount = getInt("Fn")
    val foundations = (0 to foundationCount - 1).map { i =>
      val prefix = "F" + i
      val numPiles = getInt(prefix + "n") match {
        case -1 => 4 * deckOptions.numDecks
        case x => x
      }
      FoundationRules(
        name = getString(prefix + "Nm"),
        numPiles = numPiles,
        lowRank = getInt(prefix + "b") match {
          case 20 => FoundationLowRank.AnyCard
          case 21 => FoundationLowRank.DeckLowRank
          case 22 => FoundationLowRank.DeckHighRank
          case 23 => FoundationLowRank.Ascending
          case _ => FoundationLowRank.SpecificRank(Rank.King)
        },
        initialCards = getInt(prefix + "d") match {
          case -1 => numPiles
          case ic => ic
        },
        suitMatchRule = getSuitMatchRule(getInt(prefix + "s")),
        rankMatchRule = getRankMatchRule(getInt(prefix + "r")),
        wrapFromKingToAce = getBoolean(prefix + "w"),
        moveCompleteSequencesOnly = getBoolean(prefix + "cs"),
        maxCards = getInt(prefix + "m"),
        canMoveFrom = try {
          getInt(prefix + "mb") match {
            case 0 => FoundationCanMoveFrom.Never
            case 1 => FoundationCanMoveFrom.Always
            case 2 => FoundationCanMoveFrom.EmptyStock
          }
        } catch {
          case x: IllegalArgumentException => getBoolean(prefix + "mb") match {
            case false => FoundationCanMoveFrom.Never
            case true => FoundationCanMoveFrom.Always
          }
        },
        mayMoveToFrom = PolitaireLookup.parseBitmask("F0o", getInt(prefix + "o")),
        visible = !getBoolean(prefix + "i"),
        autoMoveCards = getBoolean(prefix + "a"),
        autoMoveFrom = PolitaireLookup.parseBitmask("F0ao", getInt(prefix + "ao"))
      )
    }.toSeq
    foundations
  }
}
