package models.rules.impl

import models.rules._

object Needle extends GameRules(
  id = "needle",
  completed = false,
  title = "Needle",
  like = Some("haystack"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/needle.htm")),
  layout = "f|tt",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 18
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUU",
        "UU",
        "",
        "UU",
        "UUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
