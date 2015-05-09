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
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 11 (Remove pairs adding to 14)
 *   Related games (related): juvenile, doublefourteens, triplefourteens, tensout
 */
object FourteenOut extends GameRules(
  id = "fourteenout",
  title = "Fourteen Out",
  related = Seq("juvenile", "doublefourteens", "triplefourteens", "tensout"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fourteen_out.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fourteen_out.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/block_fourteen.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/take_fourteen.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Fourteen.html.en")
  ),
  description = "An interesting game in which you remove pairs that add the fourteen.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
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
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
