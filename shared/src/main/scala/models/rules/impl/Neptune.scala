package models.rules.impl

import models.card.Suit
import models.rules._

object Neptune extends GameRules(
  id = "neptune",
  completed = false,
  title = "Neptune",
  related = Seq("shuffle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/neptune.htm")),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveRankPairs,
  deckOptions = DeckOptions(numDecks = 4, suits = Seq(Suit.Moons)),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
