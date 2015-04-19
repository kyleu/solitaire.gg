package utils.parser

import models.game._
import models.game.rules._

class PolitaireGameRulesParser(variant: PolitaireParser.Variant) {
  def parse() = try {
    parseInternal()
  } catch {
    case x: Exception => throw new IllegalStateException("Exception [" + x.getClass.getSimpleName + ": " +  x.getMessage + "] encountered while parsing [" + variant.id + "].", x)
  }

  private[this] def parseInternal() = {
    val lowRank = {
      val lowChar = getString("lowpip").headOption.getOrElse(throw new IllegalStateException())
      if(lowChar == '.') { None } else { Some(Rank.fromChar(lowChar)) }
    }

    val deckOptions = DeckOptions(
      numDecks = getInt("ndecks"),
      suits = Suit.all,
      ranks = Rank.all,
      lowRank = lowRank
    )

    val foundationCount = getInt("Fn")

    val foundations = (0 to foundationCount - 1).map { i =>
      val prefix = "F" + i
      FoundationSet(
        numPiles = getInt(prefix + "n"),
        lowRank = getInt(prefix + "b") match {
          case 20 => FoundationLowRank.AnyCard
          case 21 => FoundationLowRank.DeckLowRank
          case 22 => FoundationLowRank.DeckHighRank
          case 23 => FoundationLowRank.Ascending
          case _ => FoundationLowRank.SpecificRank(Rank.King)
        },
        initialCards = getInt(prefix + "d"),
        suitMatchRule = getInt(prefix + "s") match {
          case 1 => SuitMatchRule.SameSuit
          case 2 => SuitMatchRule.DifferentSuits
          case 3 => SuitMatchRule.SameColor
          case 4 => SuitMatchRule.AlternatingColors
          case 5 => SuitMatchRule.Any
          case x => throw new IllegalArgumentException(x.toString)
        },
        rankMatchRule = getInt(prefix + "r") match {
          case 128 => RankMatchRule.Up
          case 32 => RankMatchRule.Down
          case 64 =>  RankMatchRule.Equal
          case 160 => RankMatchRule.UpOrDown
          case 96 => RankMatchRule.Any
          case 256 => RankMatchRule.UpBy2
          case 16 => RankMatchRule.DownBy2
          case 512 => RankMatchRule.UpBy3
          case 8 => RankMatchRule.DownBy3
          case 1024 => RankMatchRule.UpBy4
          case 4 => RankMatchRule.DownBy4
          case 8192 => RankMatchRule.UpByPileIndex
          case 8191 => RankMatchRule.Any
          case x => throw new IllegalArgumentException(x.toString)
        }
      )
    }.toSeq

    GameRules(
      id = variant.id,
      title = getString("title"),
      description = getString("desc"),
      victoryCondition = getInt("victory") match {
        case 0 => VictoryCondition.AllOnFoundation
        case 1 => VictoryCondition.AllButFourCardsOnFoundation
        case 2 => VictoryCondition.NoneInStock
        case 3 => VictoryCondition.AllOnTableauSorted
        case 4 => VictoryCondition.NoneInPyramid
        case 5 => VictoryCondition.AllOnFoundationOrStock
        case x => throw new IllegalArgumentException(x.toString)
      },
      cardRemovalMethod = getInt("pairs") match {
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
      },
      deckOptions = deckOptions,
      foundations = foundations
    )
  }

  private[this] def getString(key: String) = {
    val attr = variant.attributes(key)
    attr.translation.getOrElse(attr.value match {
      case s: String => s
      case x => throw new IllegalArgumentException("Value [" + x + "] is not a string.")
    })
  }
  private[this] def getInt(key: String) = variant.attributes(key).value match {
    case i: Int => i
    case s: String if s == "0x0020|0x0080" => 160
    case s: String if s.startsWith("0x") => Integer.parseInt(s.trim().substring(2), 16)
    case x => throw new IllegalArgumentException("Invalid integer [" + x + "].")
  }
}
