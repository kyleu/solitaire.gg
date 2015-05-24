// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 3
 *   Related games (related): dover
 */
object Bristol extends GameRules(
  id = "bristol",
  title = "Bristol",
  related = Seq("dover"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Bristol_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bristol.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bristol.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/bristol.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bristol.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Bristol.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/bristol.php"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Bristol.html.en")
  ),
  description = "A game with three waste piles invented by Albert Morehead and Geoffrey Mott-Smith.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
