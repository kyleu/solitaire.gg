package models.game.rules

import models.game.pile._

object GameRules {
  val allSources = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
}

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
  pyramids: Seq[PyramidRules] = Nil,
  complete: Boolean = false
) {
  lazy val prototypePileSets = {
    stock.map( s => StockSet(s)) ++
    waste.map( w =>  WasteSet(w)) ++
    reserves.map(r => ReserveSet(r)) ++
    cells.map(c => CellSet(c)) ++
    foundations.map(f => FoundationSet(f)) ++
    tableaus.map(t => TableauSet(t)) ++
    pyramids.map(p => PyramidSet(p))
  }.toSeq
}
