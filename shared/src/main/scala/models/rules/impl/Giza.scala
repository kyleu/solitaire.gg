package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Giza extends GameRules(
  id = "giza",
  completed = false,
  title = "Giza",
  like = Some("pyramid"),
  related = Seq("pyramiddozen"),
  links = Seq(
    Link("Michael Keller's Discussion at Solitaire Laboratory", "www.solitairelaboratory.com/giza.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/giza.htm")
  ),
  layout = "f|t|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)
