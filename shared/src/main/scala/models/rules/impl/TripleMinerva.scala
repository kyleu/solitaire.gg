package models.rules.impl

import models.rules._

object TripleMinerva extends GameRules(
  id = "tripleminerva",
  completed = true,
  title = "Triple Minerva",
  like = Some("minerva"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_minerva.htm")),
  layout = "swf|r::t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(initialCards = 15))
)
