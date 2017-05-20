package models.rules.impl

import models.rules._

object Minerva extends GameRules(
  id = "minerva",
  completed = false,
  title = "Minerva",
  like = Some("athena"),
  related = Seq("doubleminerva", "tripleminerva", "munger", "mystique"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/minerva.htm")),
  layout = "swf|r|t",
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
