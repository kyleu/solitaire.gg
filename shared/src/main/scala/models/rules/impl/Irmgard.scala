package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): gypsy
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Irmgard extends GameRules(
  id = "irmgard",
  completed = true,
  title = "Irmgard",
  like = Some("gypsy"),
  links = Seq(Link("PySol", "pysolfc.sourceforge.net/doc/rules/irmgard.html")),
  description = "A variant of ^gypsy^ where you have an extra tableau pile, but you can only fill spaces with kings.",
  layout = "sf|t",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
