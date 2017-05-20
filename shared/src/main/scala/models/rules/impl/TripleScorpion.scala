package models.rules.impl

import models.rules._

object TripleScorpion extends GameRules(
  id = "triplescorpion",
  completed = true,
  title = "Triple Scorpion",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_scorpion.htm")),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
