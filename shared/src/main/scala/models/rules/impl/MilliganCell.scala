package models.rules.impl

import models.rules._

object MilliganCell extends GameRules(
  id = "milligancell",
  completed = true,
  title = "Milligan Cell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/milligan_cell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/milligan_cell.htm")
  ),
  layout = "sf|::.c|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
