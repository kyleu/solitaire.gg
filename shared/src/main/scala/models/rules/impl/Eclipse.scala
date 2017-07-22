package models.rules.impl

import models.rules._

object Eclipse extends GameRules(
  id = "eclipse",
  completed = false,
  title = "Eclipse",
  like = Some("waningmoon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eclipse.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
