package models.rules.impl

import models.rules._

object Carthage extends GameRules(
  id = "carthage",
  completed = true,
  title = "Carthage",
  related = Seq("algiers"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carthage.htm")),
  layout = "sf|.:t|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.TableauFirstSet, maximumDeals = Some(1), cardsDealt = StockCardsDealt.Count(2))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 1
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
