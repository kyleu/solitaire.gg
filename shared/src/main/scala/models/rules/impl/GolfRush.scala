package models.rules.impl

import models.rules._

object GolfRush extends GameRules(
  id = "golfrush",
  completed = false,
  title = "Golf Rush",
  like = Some("golf"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/golf_rush.htm"),
    Link("Jan Wolter's Experiments", "/article/golf.html")
  ),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  stock = Some(
    StockRules(
      cardsShown = 16,
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrap = false,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
