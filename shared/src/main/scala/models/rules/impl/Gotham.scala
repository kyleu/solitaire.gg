// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 3
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 3
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): newyork
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): bigapple
 */
object Gotham extends GameRules(
  id = "gotham",
  title = "Gotham",
  like = Some("newyork"),
  related = Seq("bigapple"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gotham.htm")),
  description = "An easier variation of ^newyork^ where we build regardless of suit and same-suit stacks can be moved.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      mayMoveToEmptyFrom = Seq("stock", "waste")
    )
  )
)
