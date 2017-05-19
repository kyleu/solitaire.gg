package models.rules.impl

import models.card.Rank
import models.rules._

object AmericanToad extends GameRules(
  id = "americantoad",
  completed = false,
  title = "American Toad",
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/AmericanToad.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/american_toad.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/american_toad.html")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  ),
  reserves = Some(ReserveRules(initialCards = 20, cardsFaceDown = -1))
)
