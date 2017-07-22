package models.rules.impl

import models.rules._

object SimonJester extends GameRules(
  id = "simonjester",
  completed = false,
  title = "Simon Jester",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simon_jester.htm")),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
