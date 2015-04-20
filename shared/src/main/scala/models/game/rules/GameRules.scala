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
    suits: Seq[Suit] = Suit.standard,
    ranks: Seq[Rank] = Rank.all,
    lowRank: Option[Rank] = Some(Rank.Ace)
) {
  val highRank = lowRank.map(lr => if (lr == Rank.Ace) { Rank.King } else { Rank.Ace })
}

sealed trait InitialCards
object InitialCards {
  case class Count(n: Int) extends InitialCards
  case object PileIndex extends InitialCards
  case object RestOfDeck extends InitialCards
  case object Custom extends InitialCards
}

case class GameRules(
  id: String,
  title: String,
  description: String,
  victoryCondition: VictoryCondition,
  cardRemovalMethod: CardRemovalMethod,
  deckOptions: DeckOptions,
  stock: Option[Stock],
  foundations: Seq[FoundationSet],
  tableaus: Seq[TableauSet]
)
