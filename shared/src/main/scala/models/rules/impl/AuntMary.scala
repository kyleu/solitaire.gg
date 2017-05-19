package models.rules.impl

import models.rules._

object AuntMary extends GameRules(
  id = "auntmary",
  completed = true,
  title = "Aunt Mary",
  like = Some("thoughtful"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/aunt_mary.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/aunt_mary.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/aunt-mary.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Aunt_Mary.html.en"),
    Link("L.Schaffer on Hobby Hub", "www.hobbyhub360.com/index.php/how-to-play-aunt-mary-solitaire-14352/")
  ),
  layout = "swf|.t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
