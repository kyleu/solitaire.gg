package models.rules.impl

import models.rules._

object LincolnGreens extends GameRules(
  id = "lincolngreens",
  completed = true,
  title = "Lincoln Greens",
  like = Some("puttputt"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lincoln_greens.htm")),
  layout = "s::::.f|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  deckOptions = DeckOptions(numDecks = 4),
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
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
