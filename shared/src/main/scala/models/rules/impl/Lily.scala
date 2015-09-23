package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): trillium
 *   Number of decks (ndecks): 2 (2 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object Lily extends GameRules(
  id = "lily",
  completed = true,
  title = "Lily",
  like = Some("trillium"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lily.htm")),
  description = "A harder variation of ^trillium^ where spaces may only be filled with kings.",
  layout = "s.:f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
