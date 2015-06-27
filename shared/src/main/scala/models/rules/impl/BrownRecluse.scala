// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of decks (ndecks): 2 (2 decks)
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object BrownRecluse extends GameRules(
  id = "brownrecluse",
  title = "Brown Recluse",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brown_recluse.htm")),
  description = "This ^spider^ variant by Thomas Warfield has a stock and a waste pile. The tableau starts with just one card in each column, but s" +
    "paces are autofilled from the waste or stock.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
