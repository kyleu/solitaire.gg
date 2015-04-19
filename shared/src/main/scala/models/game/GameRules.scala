package models.game

case class VictoryCondition()
case class CardRemovalMethod()

case class DeckOptions(
  numDecks: Int = 1,
  suits: Seq[Suit] = Suit.all,
  ranks: Seq[Rank] = Rank.all,
  lowRank: Option[Rank] = Some(Rank.Ace)
)

case class FoundationOptions(

)

case class FoundationSet(

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
