package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 3 (When all stackable cards are off)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Similar to (like): fortyandeight
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Lower48 extends GameRules(
  id = "lower48",
  completed = false,
  title = "Lower 48",
  like = Some("fortyandeight"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lower_48.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
