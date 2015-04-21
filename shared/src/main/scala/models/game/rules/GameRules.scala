package models.game.rules

case class GameRules(
  id: String,
  title: String,
  description: String,
  victoryCondition: VictoryCondition,
  cardRemovalMethod: CardRemovalMethod,
  deckOptions: DeckOptions,
  stock: Option[Stock],
  waste: Option[WasteSet],
  foundations: Seq[FoundationSet],
  tableaus: Seq[TableauSet],
  cells: Option[CellSet],
  reserves: Option[ReserveSet],
  pyramids: Seq[PyramidSet]
)
