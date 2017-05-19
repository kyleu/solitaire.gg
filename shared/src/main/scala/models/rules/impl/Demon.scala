package models.rules.impl

import models.card.Rank
import models.rules._

object Demon extends GameRules(
  id = "demon",
  completed = true,
  title = "Demon",
  like = Some("doublecanfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demon.htm")),
  layout = "swf|r::t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
  )),
  reserves = Some(ReserveRules(initialCards = 40, cardsFaceDown = -1))
)
