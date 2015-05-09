// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): auldlangsyne
 *   Number of decks (ndecks): 4 (4 decks)
 */
object LeapYear extends GameRules(
  id = "leapyear",
  title = "Leap Year",
  like = Some("auldlangsyne"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/leap_year.htm")),
  description = "A four-deck version of ^auldlangsyne^. Requires a large screen.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      initialCards = 16,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
