package models.rules.impl

import models.rules._

object AliBaba extends GameRules(
  id = "alibaba",
  completed = true,
  title = "Ali Baba",
  related = Seq("cassim"),
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/AliBaba.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ali_baba.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/ali-baba.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/ali_baba.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/ali_baba.htm"),
    Link("Jan Wolter's Experiments", "/article/alibaba.html")
  ),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
