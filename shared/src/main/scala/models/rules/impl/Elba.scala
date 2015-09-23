package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 3 (When all stackable cards are off)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Elba extends GameRules(
  id = "elba",
  completed = false,
  title = "Elba",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/elba.htm")),
  description = "A variant of ^fortythieves^ with ^klondike^-like building rules.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
