// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Gilbert extends GameRules(
  id = "gilbert",
  title = "Gilbert",
  like = Some("klondike"),
  description = "An odd ^klondike^ variation with one set of foundations building up and one set building down. The fact that only sevens can fill " +
  "gaps in the tableau makes it nearly unplayable.",
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
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = TableauFillEmptyWith.Sevens
    )
  ),
  complete = false
)

