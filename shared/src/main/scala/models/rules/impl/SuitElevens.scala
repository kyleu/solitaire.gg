package models.rules.impl

import models.rules._

object SuitElevens extends GameRules(
  id = "suitelevens",
  completed = false,
  title = "Suit Elevens",
  like = Some("elevens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/suit_elevens.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
