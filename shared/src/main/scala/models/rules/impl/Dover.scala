package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 1 (In same suit)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 3
 *   Similar to (like): bristol
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Dover extends GameRules(
  id = "dover",
  completed = false,
  title = "Dover",
  like = Some("bristol"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dover.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
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
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
