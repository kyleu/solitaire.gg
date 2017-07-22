package models.rules.impl

import models.rules._

object QuadrupleYukon extends GameRules(
  id = "quadrupleyukon",
  completed = true,
  title = "Quadruple Yukon",
  like = Some("doubleyukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_yukon.htm")),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 4),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
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
        "DDDDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDDDUUUUU",
        "DDDDDDDDDDDDDDUUUUU",
        "DDDDDDDDDDDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
