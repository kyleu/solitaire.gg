// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

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
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): labellelucie
 */
object SuperFlowerGarden extends GameRules(
  id = "superflowergarden",
  completed = false,
  title = "Super Flower Garden",
  like = Some("labellelucie"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/super_flower_gar.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/super_flower_garden.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/super-flower-garden.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/super_flower_garden.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SuperFlowerGarden.htm")
  ),
  description = "This is an easier version of ^labellelucie^ in which one may build regardless of suit. It is unrelated to ^flowergarden^.",
  layout = "f|t",
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)