package models.rules.impl

import models.rules._

object PantherCreek extends GameRules(
  id = "panthercreek",
  completed = false,
  title = "Panther Creek",
  like = Some("golf"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/panther_creek.htm")),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      cardsShown = 16,
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
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
  tableaus = IndexedSeq(
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
