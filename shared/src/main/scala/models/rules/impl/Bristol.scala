package models.rules.impl

import models.rules._

object Bristol extends GameRules(
  id = "bristol",
  completed = true,
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
  layout = "sw::f|.t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules(numPiles = 3)),
  foundations = Seq(FoundationRules(numPiles = 4, suitMatchRule = SuitMatchRule.Any, autoMoveCards = true)),
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
