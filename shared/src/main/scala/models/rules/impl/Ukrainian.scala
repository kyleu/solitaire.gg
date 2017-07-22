package models.rules.impl

import models.rules._

object Ukrainian extends GameRules(
  id = "ukrainian",
  completed = true,
  title = "Ukrainian",
  like = Some("russian"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ukrainian_solitaire.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/UkrainianSolitaire.htm")
  ),
  layout = ":.f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
