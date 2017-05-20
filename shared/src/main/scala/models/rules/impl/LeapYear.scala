package models.rules.impl

import models.rules._

object LeapYear extends GameRules(
  id = "leapyear",
  completed = false,
  title = "Leap Year",
  like = Some("auldlangsyne"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/leap_year.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      initialCards = 16,
      suitMatchRule = SuitMatchRule.Any
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
