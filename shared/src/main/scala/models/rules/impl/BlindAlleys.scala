package models.rules.impl

import models.rules._

object BlindAlleys extends GameRules(
  id = "blindalleys",
  completed = false,
  title = "Blind Alleys",
  related = Seq("passeul"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/blind_alleys.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/blind_alleys.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/blind_alleys.htm")
  ),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(numPiles = 6, initialCards = InitialCards.Count(3)))
)
