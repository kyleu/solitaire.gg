package utils.parser.politaire

import models.game._
import models.game.rules._

class GameRulesParser(val variant: PolitaireParser.Variant) extends GameRulesParserHelper {
  def parse() = try {
    parseInternal()
  } catch {
    case x: Exception =>
      throw new IllegalStateException("Exception [" + x.getClass.getSimpleName + ": " + x.getMessage + "] encountered while parsing [" + variant.id + "].", x)
  }
  private[this] def parseInternal() = {
    val deckOptions = DeckOptions(
      numDecks = getInt("ndecks"),
      suits = PolitaireTranslations.parseBitmask("suits", getInt("stdsuits")).map(x => Suit.fromChar(x.head)).sortBy(_.value),
      ranks = PolitaireTranslations.parseBitmask("ranks", getInt("ranks")).map(x => Rank.fromChar(x.head)).sortBy(_.value),
      lowRank = {
        val lowChar = getString("lowpip").headOption.getOrElse(throw new IllegalStateException())
        if (lowChar == '.') { None } else { Some(Rank.fromChar(lowChar)) }
      }
    )

    val victoryCondition = getInt("victory") match {
      case 0 => VictoryCondition.AllOnFoundation
      case 1 => VictoryCondition.AllButFourCardsOnFoundation
      case 2 => VictoryCondition.NoneInStock
      case 3 => VictoryCondition.AllOnTableauSorted
      case 4 => VictoryCondition.NoneInPyramid
      case 5 => VictoryCondition.AllOnFoundationOrStock
      case x => throw new IllegalArgumentException(x.toString)
    }

    val cardRemovalMethod = getInt("pairs") match {
      case 0 => CardRemovalMethod.BuildSequencesOnFoundation
      case 1 => CardRemovalMethod.RemovePairsOfSameRank
      case 2 => CardRemovalMethod.RemovePairsOfSameRankAndColor
      case 3 => CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK
      case 4 => CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair
      case 5 => CardRemovalMethod.RemovePairsAddingToTenOrJQK
      case 6 => CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK
      case 7 => CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair
      case 8 => CardRemovalMethod.RemovePairsAddingToElevenOrJQK
      case 9 => CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK
      case 10 => CardRemovalMethod.RemovePairsAddingToThirteenOrK
      case 11 => CardRemovalMethod.RemovePairsAddingToFourteen
      case 12 => CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK
      case 13 => CardRemovalMethod.RemovePairsAddingToFifteenOrAPair
      case 14 => CardRemovalMethod.RemovePairsAddingToSeventeenOrA23
      case 15 => CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen
      case 16 => CardRemovalMethod.RemoveConsecutiveRankPairs
      case 17 => CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs
      case 18 => CardRemovalMethod.RemovePairsAddingToTwelveOrQK
      case 20 => CardRemovalMethod.StackSameRankOrSuitInWaste
      case 21 => CardRemovalMethod.RemovePairsOfSameSuit
      case 22 => CardRemovalMethod.RemoveConsecutiveRankPairsOrAK
      case 23 => CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK
      case 24 => CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK
      case x => throw new IllegalArgumentException(x.toString)
    }

    GameRules(
      id = variant.id,
      title = getString("title"),
      description = getString("desc"),
      victoryCondition = victoryCondition,
      cardRemovalMethod = cardRemovalMethod,
      deckOptions = deckOptions,
      foundations = getFoundations(deckOptions),
      tableaus = getTableaus(deckOptions)
    )
  }
}
