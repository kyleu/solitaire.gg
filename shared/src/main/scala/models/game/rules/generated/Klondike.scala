// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Klondike extends GameRules(
  id = "klondike",
  title = "Klondike",
  related = Seq("athena", "chinaman", "chineseklondike", "endlessharp", "gilbert", "goldmine", "jumboklondike", "kingsley", "klondikegallery", "saratoga", "smokey", "spike", "thoughtful", "trigon", "whitehorse"),
  description = "The world's most famous solitaire game features a triangular tableau where you build down in alternating colors.",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

