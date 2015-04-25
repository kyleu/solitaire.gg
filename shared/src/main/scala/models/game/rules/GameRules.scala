package models.game.rules

case class GameRules(
  id: String,
  title: String,
  description: String,
  victoryCondition: VictoryCondition = VictoryCondition.AllOnFoundation,
  cardRemovalMethod: CardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions: DeckOptions = DeckOptions(),
  stock: Option[StockRules] = None,
  waste: Option[WasteRules] = None,
  foundations: Seq[FoundationRules] = Nil,
  tableaus: Seq[TableauRules] = Nil,
  cells: Option[CellRules] = None,
  reserves: Option[ReserveRules] = None,
  pyramids: Seq[PyramidRules] = Nil
)
