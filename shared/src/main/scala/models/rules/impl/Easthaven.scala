package models.rules.impl

import models.rules._

object Easthaven extends GameRules(
  id = "easthaven",
  completed = true,
  title = "Easthaven",
  related = Seq("tripleeasthaven", "doubleeasthaven"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/easthaven.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Easthaven.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/easthaven.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Easthaven.html.en"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/easthaven.htm")
  ),
  layout = "s.f|t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(initialCards = InitialCards.Count(3)))
)
