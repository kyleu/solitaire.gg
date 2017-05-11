package models.rules

import enumeratum.values._

sealed abstract class PyramidType(val value: Int) extends IntEnumEntry
object PyramidType extends IntEnum[PyramidType] with IntUPickleEnum[PyramidType] {
  case object Standard extends PyramidType(1)
  case object Inverted extends PyramidType(2)
  override val values = findValues
}

sealed abstract class PyramidFaceDownCards(val value: Int) extends IntEnumEntry
object PyramidFaceDownCards extends IntEnum[PyramidFaceDownCards] with IntUPickleEnum[PyramidFaceDownCards] {
  case object AllButLastRow extends PyramidFaceDownCards(1)
  case object EvenNumbered extends PyramidFaceDownCards(2)
  case object OddNumbered extends PyramidFaceDownCards(3)
  case class Count(n: Int) extends PyramidFaceDownCards(4)
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
