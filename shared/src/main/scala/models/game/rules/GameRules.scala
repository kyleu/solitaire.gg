package models.game.rules

object GameRules {
  val default = GameRules("default", "Default Game", "")
  val allSources = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation", "tableau")
}

case class Link(title: String, url: String)

case class GameRules(
  id: String,
  title: String,
  description: String,
  like: Option[String] = None,
  related: Seq[String] = Nil,
  links: Seq[Link] = Nil,
  victoryCondition: VictoryCondition = VictoryCondition.AllOnFoundation,
  cardRemovalMethod: CardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions: DeckOptions = DeckOptions(),
  stock: Option[StockRules] = None,
  waste: Option[WasteRules] = None,
  reserves: Option[ReserveRules] = None,
  cells: Option[CellRules] = None,
  foundations: Seq[FoundationRules] = Nil,
  tableaus: Seq[TableauRules] = Nil,
  pyramids: Seq[PyramidRules] = Nil,
  special: Option[SpecialRules] = None,
  layout: Option[String] = None,
  completed: Boolean = false
) extends GameRulesHelper
