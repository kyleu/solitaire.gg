package models.rules.impl

import models.rules._

object Athena extends GameRules(
  id = "athena",
  completed = false,
  title = "Athena",
  like = Some("klondike"),
  related = Seq("bureau", "minerva"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/athena.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Athena.html.en"),
    Link("Elton Gahr on HobbyHub", "www.solitairelaboratory.com/buildingranks.html")
  ),
  layout = "swf|t",
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
