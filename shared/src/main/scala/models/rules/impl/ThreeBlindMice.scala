package models.rules.impl

import models.rules._

object ThreeBlindMice extends GameRules(
  id = "threeblindmice",
  completed = false,
  title = "Three Blind Mice",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/three_blind_mice.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/three_blind_mice.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/three-blind-mice.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Scorpion_(solitaire)")
  ),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUU",
        "DDDUU",
        "DDDUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
