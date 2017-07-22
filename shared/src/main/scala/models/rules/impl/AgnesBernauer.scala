package models.rules.impl

import models.card.Rank
import models.rules._

object AgnesBernauer extends GameRules(
  id = "agnesbernauer",
  completed = true,
  title = "Agnes Bernauer",
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/AgnesBernauer.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Agnes_(card_game)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/agnes_bernauer.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/agnes.html")
  ),
  layout = "s:f|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(dealTo = StockDealTo.Reserve, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(numPiles = 7, cardsFaceDown = -1))
)
