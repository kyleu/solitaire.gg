package models.rules.impl

import models.rules._

object GoldMine extends GameRules(
  id = "goldmine",
  completed = true,
  title = "Gold Mine",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gold_mine.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Gold_Mine.html.en")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(0)
    )
  )
)
