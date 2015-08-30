// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Deal order (RDd): 0 (Rows, left to right, bottom to top)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 3 (Columns, right to left, bottom to top)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Ranks in use (ranks): 8129
 */
object Strata extends GameRules(
  id = "strata",
  completed = false,
  title = "Strata",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/strata.htm")),
  description = "An eight-by-eight square tableau, a short deck, and two redeals make this game interesting.",
  layout = Some("wf|t"),
  deckOptions = DeckOptions(
    numDecks = 2,
    ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)