package models.rules.impl

import models.rules._

object Canister extends GameRules(
  id = "canister",
  completed = true,
  title = "Canister",
  related = Seq("britishcanister", "americancanister", "bucket"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canister.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/canister.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/canister.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Canister.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/canister.htm")
  ),
  layout = "::f|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
