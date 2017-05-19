package models.rules.impl

import models.card.Rank
import models.rules._

object CanfieldRush extends GameRules(
  id = "canfieldrush",
  completed = true,
  title = "Canfield Rush",
  like = Some("canfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canfield_rush.htm")),
  layout = "swf|:r:t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.FewerEachTime)),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
