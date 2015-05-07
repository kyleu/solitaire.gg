package models.game.rules

sealed trait CardRemovalMethod

object CardRemovalMethod {
  case object BuildSequencesOnFoundation extends CardRemovalMethod

  case object RemovePairsOfSameRank extends CardRemovalMethod
  case object RemovePairsOfSameRankAndColor extends CardRemovalMethod
  case object RemovePairsOfSameSuit extends CardRemovalMethod

  case object RemoveNinesOrPairsAddingToNineOr10JQK extends CardRemovalMethod

  case object RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair extends CardRemovalMethod
  case object RemovePairsAddingToTenOr10JQK extends CardRemovalMethod
  case object RemovePairsAddingToTenOrFour10JQK extends CardRemovalMethod

  case object RemovePairsAddingToElevenOrJPairOrQK extends CardRemovalMethod
  case object RemovePairsAddingToElevenOrJPairOrQPairOrKPair extends CardRemovalMethod
  case object RemovePairsAddingToElevenOrJQK extends CardRemovalMethod
  case object RemoveSameSuitPairsAddingToElevenOrJQK extends CardRemovalMethod

  case object RemovePairsAddingToTwelveOrQK extends CardRemovalMethod

  case object RemovePairsAddingToThirteenOrK extends CardRemovalMethod

  case object RemovePairsAddingToFourteen extends CardRemovalMethod

  case object RemoveSetsAddingToFifteenOr10JQK extends CardRemovalMethod
  case object RemoveSetsAddingToFifteenOrFour10JQK extends CardRemovalMethod
  case object RemovePairsAddingToFifteenOrAPair extends CardRemovalMethod

  case object RemovePairsAddingToSeventeenOrA23 extends CardRemovalMethod

  case object RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen extends CardRemovalMethod

  case object RemoveConsecutiveRankPairs extends CardRemovalMethod
  case object RemoveConsecutiveRankPairsOrAK extends CardRemovalMethod
  case object RemoveConsecutiveOrEqualRankPairs extends CardRemovalMethod
  case object StackSameRankOrSuitInWaste extends CardRemovalMethod
}
