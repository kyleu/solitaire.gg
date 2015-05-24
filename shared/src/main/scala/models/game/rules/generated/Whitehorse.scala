// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 */
object Whitehorse extends GameRules(
  id = "whitehorse",
  title = "Whitehorse",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/whitehorse.htm")),
  description = "An easy ^klondike^ variant where instead of dealing a lot of cards to the tableau, we have spaces that autofill",
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
      initialCards = InitialCards.Count(1),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
