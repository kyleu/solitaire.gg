package models.rules.impl

import models.rules._

object Gilbert extends GameRules(
  id = "gilbert",
  completed = false,
  title = "Gilbert",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gilbert.htm")),
  layout = "swff|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.Sevens
    )
  )
)
