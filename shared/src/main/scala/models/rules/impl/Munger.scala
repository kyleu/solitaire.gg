package models.rules.impl

import models.rules._

object Munger extends GameRules(
  id = "munger",
  completed = false,
  title = "Munger",
  like = Some("minerva"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/munger.htm")),
  layout = "swf|r|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(initialCards = 7))
)
