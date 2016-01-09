package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): limited
 *   Number of decks (ndecks): 4 (4 decks)
 */
object DoubleLimited extends GameRules(
  id = "doublelimited",
  completed = true,
  title = "Double Limited",
  like = Some("limited"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_limited.htm")),
  description = "A four-deck version of ^limited^. A large screen will be needed.",
  layout = "swf|.:t",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
