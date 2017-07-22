package models.rules.impl

import models.rules._

object DoubleScorpion extends GameRules(
  id = "doublescorpion",
  completed = true,
  title = "Double Scorpion",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_scorpion.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-scorpion.htm")
  ),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, moveCompleteSequencesOnly = true, canMoveFrom = FoundationCanMoveFrom.Never, visible = false)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 10,
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "DDDDUUUUUUU",
      "DDDDUUUUUUU",
      "DDDDUUUUUUU",
      "DDDDUUUUUUU",
      "DDDDUUUUUU",
      "UUUUUUUUUU",
      "UUUUUUUUUU",
      "UUUUUUUUUU",
      "UUUUUUUUUU",
      "UUUUUUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
