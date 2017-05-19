package models.rules.impl

import models.rules._

object DoubleMinerva extends GameRules(
  id = "doubleminerva",
  completed = true,
  title = "Double Minerva",
  like = Some("minerva"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_minerva.htm")),
  layout = "swf|r:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13))
)
