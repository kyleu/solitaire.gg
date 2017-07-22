package models.rules.impl

import models.rules._

object EightByEight extends GameRules(
  id = "eightbyeight",
  completed = true,
  title = "Eight by Eight",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eight_by_eight.htm")),
  layout = "sw|f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.FewerEachTime)),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.Count(8),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    emptyFilledWith = FillEmptyWith.None
  ))
)
