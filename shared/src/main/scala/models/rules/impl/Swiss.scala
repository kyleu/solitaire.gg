package models.rules.impl

import models.card.Rank
import models.rules._

object Swiss extends GameRules(
  id = "swiss",
  completed = false,
  title = "Swiss",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/swiss_patience.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SwissPatience.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(lowRank = Rank.Two),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "DDU",
        "DDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
