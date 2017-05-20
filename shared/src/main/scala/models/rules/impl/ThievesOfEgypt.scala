package models.rules.impl

import models.rules._

object ThievesOfEgypt extends GameRules(
  id = "thievesofegypt",
  completed = true,
  title = "Thieves of Egypt",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thieves_of_egypt.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/thieves_of_egypt.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/thieves_of_egypt.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thieves-of-egypt.htm")
  ),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UUU",
        "UUUUU",
        "UUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUU",
        "UUUUUU",
        "UUUU",
        "UU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
