package services.help

import models.game.rules.{ CardRemovalMethod, VictoryCondition }

object ObjectiveHelpService {
  def objective(vc: VictoryCondition, crm: CardRemovalMethod) = vc match {
    case VictoryCondition.AllButFourCardsOnFoundation => "Place all but four cards on the foundation."
    case VictoryCondition.AllOnFoundation => crm match {
      case CardRemovalMethod.BuildSequencesOnFoundation => "Place all cards on the foundation."
      case CardRemovalMethod.RemovePairsOfSameRank => "Remove all pairs with equal ranks."
      case CardRemovalMethod.RemovePairsOfSameRankAndColor => "Remove all pairs with equal ranks and the same color."
      case CardRemovalMethod.RemovePairsOfSameSuit => "Remove all pairs with the same suit."
      case CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK => "Remove all pairs adding to nine and all Nines, Tens, Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair =>
        "Remove all pairs adding to ten, pairs of Jacks, pairs of Queens, and pairs of Kings,"
      case CardRemovalMethod.RemovePairsAddingToTenOr10JQK => "Remove all pairs adding to ten, and all Tens, Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK => "Remove all pairs adding to ten, and sets of all four Tens, Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair => "Remove all pairs adding to eleven, and all pairs of Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK => "Remove all pairs adding to eleven, all pairs of Jacks, and all Queen/King pairs."
      case CardRemovalMethod.RemovePairsAddingToElevenOrJQK => "Remove all pairs adding to eleven, and all Jacks, Queens, and Kings."
      case CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK => "Remove all same suitpairs adding to eleven, and all Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToTwelveOrQK => "Remove all pairs adding to twelve, and all Queens and Kings."
      case CardRemovalMethod.RemovePairsAddingToThirteenOrK => "Remove all pairs adding to 13, and all Kings."
      case CardRemovalMethod.RemovePairsAddingToFourteen => "Remove all pairs adding to 14."
      case CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK => "Remove all pairs adding to 15, and all sets of Ten/Jack/Queen/King."
      case CardRemovalMethod.RemovePairsAddingToFifteenOrAPair => "Remove all pairs adding to 15, and all Ace pairs."
      case CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK => "Remove sets adding to fifteen, and sets of all four Tens, Jacks, Queens, and Kings."
      case CardRemovalMethod.RemovePairsAddingToSeventeenOrA23 => "Remove all pairs adding to 17, and all sets of Ace/Two/Three."
      case CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen => "Remove all sets of one face card and thre others that add to 18."
      case CardRemovalMethod.RemoveConsecutiveRankPairs => "Remove all pairs with consecutive ranks."
      case CardRemovalMethod.RemoveConsecutiveRankPairsOrAK => "Remove all pairs with consecutive ranks."
      case CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs => "Remove all pairs with consecutive or equal ranks."
      case CardRemovalMethod.StackSameRankOrSuitInWaste => "Stack cards of same rank and suit in the waste."
    }
    case VictoryCondition.AllOnFoundationOrStock => "Place all cards on the foundation or stock."
    case VictoryCondition.AllOnTableauSorted => "Sort all cards on the tableau or move them to the foundation."
    case VictoryCondition.NoneInPyramid => "Remove all cards from the pyramid."
    case VictoryCondition.NoneInStock => "Remove all cards from the stock."
  }
}
