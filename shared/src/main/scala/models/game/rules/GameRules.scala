package models.game.rules

import models.game._

sealed trait VictoryCondition
object VictoryCondition {
  case object AllOnFoundation extends VictoryCondition
  case object AllButFourCardsOnFoundation extends VictoryCondition
  case object AllOnFoundationOrStock extends VictoryCondition
  case object NoneInStock extends VictoryCondition
  case object NoneInPyramid extends VictoryCondition
  case object AllOnTableauSorted extends VictoryCondition
}

case class DeckOptions(
  numDecks: Int = 1,
  suits: Seq[Suit] = Suit.all,
  ranks: Seq[Rank] = Rank.all,
  lowRank: Option[Rank] = Some(Rank.Ace)
) {
  val highRank = lowRank.map(lr => if(lr == Rank.Ace) { Rank.King } else { Rank.Ace })
}

sealed trait FoundationLowRank
object FoundationLowRank {
  case object AnyCard extends FoundationLowRank
  case object DeckLowRank extends FoundationLowRank
  case object DeckHighRank extends FoundationLowRank
  case object Ascending extends FoundationLowRank
  case class SpecificRank(r: Rank) extends FoundationLowRank
}

case class FoundationSet(
  numPiles: Int,
  lowRank: FoundationLowRank,
  initialCards: Int,
  suitMatchRule: SuitMatchRule,
  rankMatchRule: RankMatchRule
)

case class GameRules(
  id: String,
  title: String,
  description: String,
  victoryCondition: VictoryCondition,
  cardRemovalMethod: CardRemovalMethod,
  deckOptions: DeckOptions,
  foundations: Seq[FoundationSet]
)
