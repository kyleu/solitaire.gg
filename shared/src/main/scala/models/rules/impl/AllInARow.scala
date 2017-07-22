package models.rules.impl

import models.rules._

object AllInARow extends GameRules(
  id = "allinarow",
  completed = false,
  title = "All in a Row",
  like = Some("golf"),
  related = Seq("blackhole"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/All_in_a_Row_(Solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/all_in_a_row.htm"),
    Link("Shlomi Fish's Solver", "www.shlomifish.org/open-source/projects/black-hole-solitaire-solver/"),
    Link("Jan Wolter's Experiments", "/article/blackhole.html")
  ),
  layout = "f|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  foundations = IndexedSeq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
