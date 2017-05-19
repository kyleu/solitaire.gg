package models.rules.impl

import models.rules._

object AmericanCanister extends GameRules(
  id = "americancanister",
  completed = true,
  title = "American Canister",
  like = Some("canister"),
  links = Seq(
    Link("Zonora", "www.zonora.com/games/a/american-canister.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/american_canister.htm")
  ),
  layout = "::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
