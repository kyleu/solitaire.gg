// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Deal order (RDd): 9 (Columns, left to right, top to bottom)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU U
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): threeshufflesandadraw, intelligence, linus, trefoil, superflowergarden
 */
object LaBelleLucie extends GameRules(
  id = "labellelucie",
  title = "La Belle Lucie",
  related = Seq("threeshufflesandadraw", "intelligence", "linus", "trefoil", "superflowergarden"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/la_belle_lucie.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/la_belle_lucie.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/La_Belle_Lucie"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/la_belle_lucie.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#belle")
  ),
  description = "A classic solitaire where you build down in suit on the tableau and can redeal twice. Rarely winnable.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom
    )
  )
)
