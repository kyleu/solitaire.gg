// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 1 (In same suit)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): BIT_STOCK
 *   Tableau piles (T0n): 6
 *   May move to non-empty tableau from (T0o): BIT_STOCK
 *   Tableau rank match rule for building (T0r): 0x1fff
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): sirtommy
 *   Related games (related): alternate, ladybetty
 */
object LadyBetty extends GameRules(
  id = "ladybetty",
  title = "Lady Betty",
  like = Some("sirtommy"),
  related = Seq("alternate", "ladybetty"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_betty.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lady_betty.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lady_betty.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/lady_betty.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lady-betty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LadyBetty.htm")
  ),
  description = "This cousin of ^sirtommy^ requires you to build the foundation in suit, but gives you two extra tableau piles to work with.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 6,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  ),
  complete = false
)

