// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   *RDc (RDc): 1
 *   *RDdo (RDdo): BIT_STOCK
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 0x2
 *   Similar to (like): acquaintance
 *   Number of decks (ndecks): 4 (4 decks)
 *   Touch interface function (touchfunc): 0x2
 */
object Quadrennial extends GameRules(
  id = "quadrennial",
  title = "Quadrennial",
  like = Some("acquaintance"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadrennial.htm")),
  description = "A version of ^leapyear^ with two redeals, or a version of ^acquaintance^ with four deck.",
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
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false
    )
  ),
  complete = false
)

