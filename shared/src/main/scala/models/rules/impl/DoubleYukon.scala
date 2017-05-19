package models.rules.impl

import models.rules._

object DoubleYukon extends GameRules(
  id = "doubleyukon",
  completed = true,
  title = "Double Yukon",
  related = Seq("quadrupleyukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/doubleyukon.htm")),
  layout = ":f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 10,
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "UUUUU",
      "DUUUUUU",
      "DDUUUUUU",
      "DDDUUUUUU",
      "DDDDUUUUUU",
      "DDDDDUUUUUU",
      "DDDDDDUUUUUU",
      "DDDDDDDUUUUUU",
      "DDDDDDDDUUUUUU",
      "DDDDDDDDDUUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
