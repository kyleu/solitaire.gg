// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object Scotch extends GameRules(
  id = "scotch",
  title = "Scotch",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scotch_patience.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/scotch_patience.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/scotch_patience.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/scotch.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ScotchPatience.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/scotch_patience.html")
  ),
  description = "Foundations build in alternate colors, tableau builds regardless of suit.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

