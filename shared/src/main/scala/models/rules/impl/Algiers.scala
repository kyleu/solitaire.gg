package models.rules.impl

import models.rules._

object Algiers extends GameRules(
  id = "algiers",
  completed = true,
  title = "Algiers",
  like = Some("carthage"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/algiers.htm")),
  layout = "f|s.t|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules(dealTo = StockDealTo.TableauFirstSet, maximumDeals = Some(1), cardsDealt = StockCardsDealt.Count(2))),
  foundations = Seq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 9,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 1
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
