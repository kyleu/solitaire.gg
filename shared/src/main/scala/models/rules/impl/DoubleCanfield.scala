package models.rules.impl

import models.card.Rank
import models.rules._

object DoubleCanfield extends GameRules(
  id = "doublecanfield",
  completed = true,
  title = "Double Canfield",
  related = Seq("variegatedcanfield", "demon"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Double_Canfield_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_canfield.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/double_canfield.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/DoubleCanfield.htm")
  ),
  layout = "swf|r:::.t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 5,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
  )),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
