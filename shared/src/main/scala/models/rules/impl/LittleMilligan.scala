package models.rules.impl

import models.rules._

object LittleMilligan extends GameRules(
  id = "littlemilligan",
  completed = false,
  title = "Little Milligan",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_milligan.htm")),
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
