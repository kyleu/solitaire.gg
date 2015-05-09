// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   *RDc (RDc): 1
 *   *RDdo (RDdo): 1
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): auldlangsyne
 *   Related games (related): quadrennial
 *   Touch interface function (touchfunc): 2
 */
object Acquaintance extends GameRules(
  id = "acquaintance",
  title = "Acquaintance",
  like = Some("auldlangsyne"),
  related = Seq("quadrennial"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/acquaintance.htm"),
    Link("Michael Smoker on HobbyHub", "www.hobbyhub360.com/index.php/acquaintance-solitaire-game-10370/")
  ),
  description = "A variation of ^auldlangsyne^ suggested by Michael Keller that adds some interest by allowing two redeals.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
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
  )
)
