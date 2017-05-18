package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): ladyjane
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): quizzie
 */
object Inquisitor extends GameRules(
  id = "inquisitor",
  completed = true,
  title = "Inquisitor",
  like = Some("ladyjane"),
  related = Seq("quizzie"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/inquisitor.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
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
      numPiles = 10,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
