package models.rules

import enumeratum.values._

sealed abstract class PyramidType(val value: String) extends StringEnumEntry
object PyramidType extends StringEnum[PyramidType] with StringUPickleEnum[PyramidType] {
  case object Standard extends PyramidType("standard")
  case object Inverted extends PyramidType("inverted")
  override val values = findValues
}

sealed abstract class PyramidFaceDownCards(val value: String) extends StringEnumEntry
object PyramidFaceDownCards extends StringEnum[PyramidFaceDownCards] with StringUPickleEnum[PyramidFaceDownCards] {
  case class Count(n: Int) extends PyramidFaceDownCards("count")
  case object AllButLastRow extends PyramidFaceDownCards("all-but-last-row")
  case object EvenNumbered extends PyramidFaceDownCards("even-numbered")
  case object OddNumbered extends PyramidFaceDownCards("odd-numbered")
  override val values = findValues
}

case class PyramidRules(
  name: String = "Pyramid",
  setNumber: Int = 0,
  pyramidType: PyramidType = PyramidType.Standard,
  height: Int = 7,
  cardsFaceDown: PyramidFaceDownCards = PyramidFaceDownCards.Count(0),

  suitMatchRuleForBuilding: SuitMatchRule = SuitMatchRule.None,
  rankMatchRuleForBuilding: RankMatchRule = RankMatchRule.None,
  wrap: Boolean = false,
  suitMatchRuleForMovingStacks: SuitMatchRule = SuitMatchRule.None,
  rankMatchRuleForMovingStacks: RankMatchRule = RankMatchRule.None,

  mayMoveToNonEmptyFrom: Seq[String] = GameRules.allSources,
  mayMoveToEmptyFrom: Seq[String] = GameRules.allSources,
  emptyFilledWith: FillEmptyWith = FillEmptyWith.None
)
