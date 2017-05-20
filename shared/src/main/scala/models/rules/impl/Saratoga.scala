package models.rules.impl

import models.rules._

object Saratoga extends GameRules(
  id = "saratoga",
  completed = false,
  title = "Saratoga",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saratoga.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/saratoga.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Saratoga.html.en"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-saratoga-13682/")
  ),
  layout = "swf|t",
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
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
