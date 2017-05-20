package models.rules.impl

import models.rules._

object Usk extends GameRules(
  id = "usk",
  completed = false,
  title = "Usk",
  like = Some("somerset"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/usk.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/usk.html")
  ),
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 1
    )
  )
)
