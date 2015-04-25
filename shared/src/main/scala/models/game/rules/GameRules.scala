package models.game.rules

case class GameRules(
  id: String,
  title: String,
  description: String,
  layout: Seq[String] = Seq.empty,
  victoryCondition: VictoryCondition,
  cardRemovalMethod: CardRemovalMethod,
  deckOptions: DeckOptions,
  stock: Option[StockRules],
  waste: Option[WasteRules],
  foundations: Seq[FoundationRules],
  tableaus: Seq[TableauRules],
  cells: Option[CellRules],
  reserves: Option[ReserveRules],
  pyramids: Seq[PyramidRules]
)
