package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 4 (To all non-empty tableau piles)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Incompatibility extends GameRules(
  id = "incompatibility",
  completed = false,
  title = "Incompatibility",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/incompatibility.htm")),
  description = "A ^spider^ game where cards can be moved to the foundation one at a time and where cards are not dealt to empty columns.",
  layout = "sf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauNonEmpty,
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
