package models.rules.impl

import models.card.Rank
import models.rules._

object TripleCanfield extends GameRules(
  id = "triplecanfield",
  completed = true,
  title = "Triple Canfield",
  related = Seq("threedemons"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_canfield.htm")),
  layout = "swf|r::::.t",
  deckOptions = DeckOptions(numDecks = 3, lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 26, cardsFaceDown = -1))
)
