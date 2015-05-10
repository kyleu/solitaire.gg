// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): 1 (Yes)
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): fourteenout
 *   Number of decks (ndecks): 2 (2 decks)
 *   Card removal method (pairs): 11 (Remove pairs adding to 14)
 */
object DoubleFourteens extends GameRules(
  id = "doublefourteens",
  title = "Double Fourteens",
  like = Some("fourteenout"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_fourteens.htm")),
  description = "An two-deck version of ^fourteenout^.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
