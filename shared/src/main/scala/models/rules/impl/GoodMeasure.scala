package models.rules.impl

import models.rules._

object GoodMeasure extends GameRules(
  id = "goodmeasure",
  completed = false,
  title = "Good Measure",
  like = Some("bakersdozen"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/good_measure.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/good_measure.htm"),
    Link("About.com (Erik Arneson)", "boardgames.about.com/od/solitaire/a/good_measure.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/GoodMeasure.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/good_measure.html")
  ),
  layout = ".f|2t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 2,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
