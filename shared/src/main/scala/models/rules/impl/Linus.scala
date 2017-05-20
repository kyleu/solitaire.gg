package models.rules.impl

import models.rules._

object Linus extends GameRules(
  id = "linus",
  completed = true,
  title = "Linus",
  like = Some("labellelucie"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/linus.htm")),
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
