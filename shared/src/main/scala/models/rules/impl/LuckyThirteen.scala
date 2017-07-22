package models.rules.impl

import models.rules._

object LuckyThirteen extends GameRules(
  id = "luckythirteen",
  completed = true,
  title = "Lucky Thirteen",
  related = Seq("luckypiles"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_thirteen.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lucky_thirteen.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LuckyThirteen.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lucky-thirteen.htm")
  ),
  layout = "::::.f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
