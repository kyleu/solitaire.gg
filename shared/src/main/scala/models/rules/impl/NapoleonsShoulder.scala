package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled from (T0fo): 2 (waste)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Number of waste piles (W0n): 1
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Similar to (like): napoleonssquare
 *   Number of decks (ndecks): 2 (2 decks)
 *   *unused (unused): temp_hack
 */
object NapoleonsShoulder extends GameRules(
  id = "napoleonsshoulder",
  completed = true,
  title = "Napoleon's Shoulder",
  like = Some("napoleonssquare"),
  links = Seq(Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/napoleons_shoulder.html")),
  layout = ".swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("waste")
    )
  )
)
