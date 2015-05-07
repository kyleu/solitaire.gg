package models.game.rules

sealed trait PyramidType
object PyramidType {
  case object Standard extends PyramidType
  case object Inverted extends PyramidType
}

sealed trait PyramidFaceDownCards
object PyramidFaceDownCards {
  case class Count(n: Int) extends PyramidFaceDownCards
  case object AllButLastRow extends PyramidFaceDownCards
  case object EvenNumbered extends PyramidFaceDownCards
  case object OddNumbered extends PyramidFaceDownCards
}

case class PyramidRules(
  name: String = "Pyramid",
  setNumber: Int = 0,
  pyramidType: PyramidType = PyramidType.Standard,
  height: Int = 7,
  cardsFaceDown: PyramidFaceDownCards = PyramidFaceDownCards.Count(0),

  suitMatchRuleForBuilding: SuitMatchRule = SuitMatchRule.None,
  rankMatchRuleForBuilding: RankMatchRule = RankMatchRule.None,
  wrapFromKingToAce: Boolean = false,
  suitMatchRuleForMovingStacks: SuitMatchRule = SuitMatchRule.None,
  rankMatchRuleForMovingStacks: RankMatchRule = RankMatchRule.None,

  mayMoveToNonEmptyFrom: Seq[String] = GameRules.allSources,
  mayMoveToEmptyFrom: Seq[String] = GameRules.allSources,
  emptyFilledWith: FillEmptyWith = FillEmptyWith.None
)
