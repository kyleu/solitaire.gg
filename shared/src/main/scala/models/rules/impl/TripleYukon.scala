package models.rules.impl

import models.rules._

object TripleYukon extends GameRules(
  id = "tripleyukon",
  completed = true,
  title = "Triple Yukon",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_yukon.htm")),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUU",
        "DUUUUUU",
        "DDUUUUUU",
        "DDDUUUUUU",
        "DDDDUUUUUU",
        "DDDDDUUUUUU",
        "DDDDDDUUUUUU",
        "DDDDDDDUUUUUU",
        "DDDDDDDDUUUUUU",
        "DDDDDDDDDUUUUUU",
        "DDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDDUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
