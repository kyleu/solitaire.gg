package models.game.rules

sealed trait PyramidType
object PyramidType {
  case object Standard extends PyramidType
  case object Inverted extends PyramidType
  case object Custom extends PyramidType
}

sealed trait PyramidFaceDownCards
object PyramidFaceDownCards {
  case class Count(n: Int) extends PyramidFaceDownCards
  case object AllButLastRow extends PyramidFaceDownCards
  case object EvenNumbered extends PyramidFaceDownCards
  case object OddNumbered extends PyramidFaceDownCards
}

sealed trait PyramidFillEmptyWith
object PyramidFillEmptyWith {
  case object Any extends PyramidFillEmptyWith
  case object None extends PyramidFillEmptyWith
  case object Kings extends PyramidFillEmptyWith
  case object KingsUntilStockEmpty extends PyramidFillEmptyWith
  case object Aces extends PyramidFillEmptyWith
  case object KingsOrAces extends PyramidFillEmptyWith
  case object Sevens extends PyramidFillEmptyWith
}

case class PyramidSet(
  name: String,
  pyramidType: PyramidType,
  height: Int,
  cardsFaceDown: PyramidFaceDownCards,

  suitMatchRuleForBuilding: SuitMatchRule,
  rankMatchRuleForBuilding: RankMatchRule,
  wrapFromKingToAce: Boolean,
  suitMatchRuleForMovingStacks: SuitMatchRule,
  rankMatchRuleForMovingStacks: RankMatchRule,

  mayMoveToNonEmptyFrom: Seq[String],
  mayMoveToEmptyFrom: Seq[String],
  emptyFilledWith: PyramidFillEmptyWith
)
