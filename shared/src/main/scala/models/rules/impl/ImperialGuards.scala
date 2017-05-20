package models.rules.impl

import models.rules._

object ImperialGuards extends GameRules(
  id = "imperialguards",
  completed = true,
  title = "Imperial Guards",
  like = Some("missmilligan"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/imperial_guards.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/imperial-guards.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ImperialGuards.htm")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      createPocketWhenEmpty = true
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
