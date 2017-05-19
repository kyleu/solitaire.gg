package models.rules.impl

import models.card.Rank
import models.rules._

object Countess extends GameRules(
  id = "countess",
  completed = false,
  title = "Countess",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/countess.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.FewerEachTime)),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  ),
  reserves = Some(ReserveRules(numPiles = 4, initialCards = 3))
)
