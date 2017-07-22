package models.rules.impl

import models.rules._

object Quadrennial extends GameRules(
  id = "quadrennial",
  completed = false,
  title = "Quadrennial",
  like = Some("acquaintance"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadrennial.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      initialCards = 16,
      suitMatchRule = SuitMatchRule.Any
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
