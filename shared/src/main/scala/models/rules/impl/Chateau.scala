package models.rules.impl

import models.rules._

object Chateau extends GameRules(
  id = "chateau",
  completed = true,
  title = "Chateau",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chateau.htm")),
  layout = "::f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
