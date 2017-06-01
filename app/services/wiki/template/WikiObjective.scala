package services.wiki.template

import models.rules.{CardRemovalMethod, GameRules, VictoryCondition}
import services.wiki.WikiService.messages

object WikiObjective {
  def objective(rules: GameRules) = {
    rules.victoryCondition match {
      case VictoryCondition.NoneInPyramid => messages("help.victory.condition.none.in.pyramid")
      case VictoryCondition.NoneInStock => messages("help.victory.condition.none.in.stock")
      case VictoryCondition.AllButFourCardsOnFoundation => messages("help.victory.condition.all.but.four.on.foundation")
      case VictoryCondition.AllOnFoundation => rules.cardRemovalMethod match {
        case CardRemovalMethod.BuildSequencesOnFoundation => messages("help.victory.condition.all.on.foundation")
        case CardRemovalMethod.RemovePairsOfSameRank => messages("help.victory.condition.pairs.sr")
        case CardRemovalMethod.RemovePairsOfSameRankAndColor => messages("help.victory.condition.pairs.srsc")
        case CardRemovalMethod.RemovePairsOfSameSuit => messages("help.victory.condition.pairs.ss")
        case CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK => messages("help.victory.condition.pairs.9-or-10jqk")
        case CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair => messages("help.victory.condition.pairs.10-or-10JQK-pairs")
        case CardRemovalMethod.RemovePairsAddingToTenOr10JQK => messages("help.victory.condition.pairs.10-or-10JQK")
        case CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK => messages("help.victory.condition.pairs.10-or-four-10JQK")
        case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair => messages("help.victory.condition.pairs.11-or-JQK-pairs")
        case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK => messages("help.victory.condition.pairs.11-or-Jpair-or-QK")
        case CardRemovalMethod.RemovePairsAddingToElevenOrJQK => messages("help.victory.condition.pairs.11-or-JQK")
        case CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK => messages("help.victory.condition.pairs.ss-11-or-JQK")
        case CardRemovalMethod.RemovePairsAddingToTwelveOrQK => messages("help.victory.condition.pairs.12-or-QK")
        case CardRemovalMethod.RemovePairsAddingToThirteenOrK => messages("help.victory.condition.pairs.13-or-K")
        case CardRemovalMethod.RemovePairsAddingToFourteen => messages("help.victory.condition.pairs.14")
        case CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK => messages("help.victory.condition.pairs.15-or-10JQK")
        case CardRemovalMethod.RemovePairsAddingToFifteenOrAPair => messages("help.victory.condition.pairs.15-or-A-pair")
        case CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK => messages("help.victory.condition.pairs.15-or-four-10JQK")
        case CardRemovalMethod.RemovePairsAddingToSeventeenOrA23 => messages("help.victory.condition.pairs.17-or-A23")
        case CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen => messages("help.victory.condition.pairs.face-and-three-that-add-to-18")
        case CardRemovalMethod.RemoveConsecutiveRankPairs => messages("help.victory.condition.pairs.cr")
        case CardRemovalMethod.RemoveConsecutiveRankPairsOrAK => messages("help.victory.condition.pairs.cr-or-AK")
        case CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs => messages("help.victory.condition.pairs.cr-or-sr")
      }
      case VictoryCondition.AllOnFoundationOrStock => messages("help.victory.condition.all.on.foundation.or.stock")
      case VictoryCondition.AllOnTableauSorted =>
        val (rmr, smr) = rules.tableaus.toList match {
          case h :: _ => (WikiMatchRule.toWords(h.rankMatchRuleForBuilding), WikiMatchRule.toWords(h.suitMatchRuleForBuilding))
          case _ => throw new IllegalStateException("Invalid number of tableau sets.")
        }
        messages("help.victory.condition.all.on.tableau.sorted", rmr, smr)
    }
  }
}
