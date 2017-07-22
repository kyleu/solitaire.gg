package models.rules.impl

import models.card.Rank
import models.rules._

object ThreeDemons extends GameRules(
  id = "threedemons",
  completed = false,
  title = "Three Demons",
  like = Some("triplecanfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/three_demons.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 3, lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(initialCards = 48, cardsFaceDown = -1))
)
