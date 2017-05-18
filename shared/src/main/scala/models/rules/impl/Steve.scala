package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): carlton
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Steve extends GameRules(
  id = "steve",
  completed = false,
  title = "Steve",
  like = Some("carlton"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/steve.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/steve.htm")
  ),
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
      numPiles = 8,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
