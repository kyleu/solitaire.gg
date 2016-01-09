package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): easthaven
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DoubleEasthaven extends GameRules(
  id = "doubleeasthaven",
  completed = true,
  title = "Double Easthaven",
  like = Some("easthaven"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_easthaven.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_easthaven.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/double_easthaven.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-easthaven.asp")
  ),
  description = "A two-deck version of ^easthaven^.",
  layout = "sf|.t",
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
      numPiles = 8,
      initialCards = InitialCards.Count(3)
    )
  )
)
