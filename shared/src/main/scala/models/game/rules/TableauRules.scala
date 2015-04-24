package models.game.rules

sealed trait TableauFaceDownCards
object TableauFaceDownCards {
  case class Count(n: Int) extends TableauFaceDownCards
  case object AllButOne extends TableauFaceDownCards
  case object EvenNumbered extends TableauFaceDownCards
  case object OddNumbered extends TableauFaceDownCards
}

sealed trait TableauAutoFillEmptyFrom
object TableauAutoFillEmptyFrom {
  case object Nowhere extends TableauAutoFillEmptyFrom
  case object Reserve extends TableauAutoFillEmptyFrom
  case object Stock extends TableauAutoFillEmptyFrom
  case object Waste extends TableauAutoFillEmptyFrom
  case object WasteThenStock extends TableauAutoFillEmptyFrom
  case object StockThenWaste extends TableauAutoFillEmptyFrom
  case object NextPile extends TableauAutoFillEmptyFrom
}

sealed trait TableauFillEmptyWith
object TableauFillEmptyWith {
  case object Any extends TableauFillEmptyWith
  case object None extends TableauFillEmptyWith
  case object Kings extends TableauFillEmptyWith
  case object KingsUntilStockEmpty extends TableauFillEmptyWith
  case object Aces extends TableauFillEmptyWith
  case object KingsOrAces extends TableauFillEmptyWith
  case object Sevens extends TableauFillEmptyWith
}

sealed trait PileAction
object PileAction {
  case object None extends PileAction
  case object MoveKingsToBottom extends PileAction
  case object MoveToFoundation extends PileAction
  case object MoveToFoundationAndReplace extends PileAction
  case object MoveToEmptyFoundation extends PileAction
  case object MoveToEmptyFoundationAndReplace extends PileAction
  case object LimitToTwoJacks extends PileAction
}

case class TableauRules(
  name: String,
  numPiles: Int,
  initialCards: InitialCards,
  cardsFaceDown: TableauFaceDownCards,
  suitMatchRuleForBuilding: SuitMatchRule,
  rankMatchRuleForBuilding: RankMatchRule,
  wrapFromKingToAce: Boolean,
  suitMatchRuleForMovingStacks: SuitMatchRule,
  rankMatchRuleForMovingStacks: RankMatchRule,

  autoFillEmptyFrom: TableauAutoFillEmptyFrom,
  emptyFilledWith: TableauFillEmptyWith,

  mayMoveToNonEmptyFrom: Seq[String],
  mayMoveToEmptyFrom: Seq[String],

  maxCards: Int,
  actionDuringDeal: PileAction,
  actionAfterDeal: PileAction,
  pilesWithLowCardsAtBottom: Int
)
