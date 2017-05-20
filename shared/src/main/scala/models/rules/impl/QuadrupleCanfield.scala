package models.rules.impl

import models.card.Rank
import models.rules._

object QuadrupleCanfield extends GameRules(
  id = "quadruplecanfield",
  completed = true,
  title = "Quadruple Canfield",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_canfield.htm")),
  layout = "swf|r::::::t",
  deckOptions = DeckOptions(numDecks = 4, lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 16, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 39, cardsFaceDown = -1))
)
