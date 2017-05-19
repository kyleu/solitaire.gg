package models.rules.impl

import models.rules._

object Brisbane extends GameRules(
  id = "brisbane",
  completed = true,
  title = "Brisbane",
  like = Some("yukon"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brisbane.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Brisbane%20Solitaire.shtml")
  ),
  layout = ":.f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUU",
        "DDDDUUUU",
        "DDDDDUUUU",
        "DDDDDDUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
