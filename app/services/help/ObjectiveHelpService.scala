package services.help

import models.rules.{ GameRules, CardRemovalMethod, VictoryCondition }
import play.api.i18n.Messages

object ObjectiveHelpService {
  def objective(rules: GameRules)(implicit messages: Messages) = rules.victoryCondition match {
    case VictoryCondition.NoneInPyramid => Messages("help.victory.condition.none.in.pyramid")
    case VictoryCondition.NoneInStock => Messages("help.victory.condition.none.in.stock")
    case VictoryCondition.AllButFourCardsOnFoundation => Messages("help.victory.condition.all.but.four.on.foundation")
    case VictoryCondition.AllOnFoundation => rules.cardRemovalMethod match {
      case CardRemovalMethod.BuildSequencesOnFoundation => Messages("help.victory.condition.all.on.foundation")
      case CardRemovalMethod.RemovePairsOfSameRank => Messages("help.victory.condition.pairs.sr")
      case CardRemovalMethod.RemovePairsOfSameRankAndColor => Messages("help.victory.condition.pairs.srsc")
      case CardRemovalMethod.RemovePairsOfSameSuit => Messages("help.victory.condition.pairs.ss")
      case CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK => Messages("help.victory.condition.pairs.9-or-10jqk")
      case CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair => Messages("help.victory.condition.pairs.10-or-10JQK-pairs")
      case CardRemovalMethod.RemovePairsAddingToTenOr10JQK => Messages("help.victory.condition.pairs.10-or-10JQK")
      case CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK => Messages("help.victory.condition.pairs.10-or-four-10JQK")
      case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair => Messages("help.victory.condition.pairs.11-or-JQK-pairs")
      case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK => Messages("help.victory.condition.pairs.11-or-Jpair-or-QK")
      case CardRemovalMethod.RemovePairsAddingToElevenOrJQK => Messages("help.victory.condition.pairs.11-or-JQK")
      case CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK => Messages("help.victory.condition.pairs.ss-11-or-JQK")
      case CardRemovalMethod.RemovePairsAddingToTwelveOrQK => Messages("help.victory.condition.pairs.12-or-QK")
      case CardRemovalMethod.RemovePairsAddingToThirteenOrK => Messages("help.victory.condition.pairs.13-or-K")
      case CardRemovalMethod.RemovePairsAddingToFourteen => Messages("help.victory.condition.pairs.14")
      case CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK => Messages("help.victory.condition.pairs.15-or-10JQK")
      case CardRemovalMethod.RemovePairsAddingToFifteenOrAPair => Messages("help.victory.condition.pairs.15-or-A-pair")
      case CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK => Messages("help.victory.condition.pairs.15-or-four-10JQK")
      case CardRemovalMethod.RemovePairsAddingToSeventeenOrA23 => Messages("help.victory.condition.pairs.17-or-A23")
      case CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen => Messages("help.victory.condition.pairs.face-and-three-that-add-to-18")
      case CardRemovalMethod.RemoveConsecutiveRankPairs => Messages("help.victory.condition.pairs.cr")
      case CardRemovalMethod.RemoveConsecutiveRankPairsOrAK => Messages("help.victory.condition.pairs.cr-or-AK")
      case CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs => Messages("help.victory.condition.pairs.cr-or-sr")
    }
    case VictoryCondition.AllOnFoundationOrStock => Messages("help.victory.condition.all.on.foundation.or.stock")
    case VictoryCondition.AllOnTableauSorted =>
      val (rmr, smr) = rules.tableaus.toList match {
        case h :: _ => (
          MatchRuleHelpService.toWords(h.rankMatchRuleForBuilding),
          MatchRuleHelpService.toWords(h.suitMatchRuleForBuilding)
        )
        case _ => throw new IllegalStateException("Invalid number of tableau sets.")
      }
      Messages("help.victory.condition.all.on.tableau.sorted", rmr, smr)
  }
}
