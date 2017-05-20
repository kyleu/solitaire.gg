package models.rules

import enumeratum.values._
import models.pile.set.PileSet

sealed abstract class TableauFaceDownCards(val value: String) extends StringEnumEntry
object TableauFaceDownCards extends StringEnum[TableauFaceDownCards] with StringCirceEnum[TableauFaceDownCards] {
  case class Count(n: Int) extends TableauFaceDownCards("count")
  case object AllButOne extends TableauFaceDownCards("all-but-one")
  case object EvenNumbered extends TableauFaceDownCards("even-numbered")
  case object OddNumbered extends TableauFaceDownCards("odd-numbered")
  override val values = findValues
}

sealed abstract class TableauAutoFillEmptyFrom(val value: String) extends StringEnumEntry
object TableauAutoFillEmptyFrom extends StringEnum[TableauAutoFillEmptyFrom] with StringCirceEnum[TableauAutoFillEmptyFrom] {
  case object Nowhere extends TableauAutoFillEmptyFrom("nowhere")
  case object Reserve extends TableauAutoFillEmptyFrom("reserve")
  case object Stock extends TableauAutoFillEmptyFrom("stock")
  case object Waste extends TableauAutoFillEmptyFrom("waste")
  case object WasteThenStock extends TableauAutoFillEmptyFrom("waste-then-stock")
  case object StockThenWaste extends TableauAutoFillEmptyFrom("stock-then-waste")
  case object NextPile extends TableauAutoFillEmptyFrom("next-pile")
  override val values = findValues
}

sealed abstract class PileAction(val value: String) extends StringEnumEntry
object PileAction extends StringEnum[PileAction] with StringCirceEnum[PileAction] {
  case object None extends PileAction("none")
  case object MoveKingsToBottom extends PileAction("move-kings-to-bottom")
  case object MoveToFoundation extends PileAction("move-to-foundation")
  case object MoveToFoundationAndReplace extends PileAction("move-to-foundation-and-replace")
  case object MoveToEmptyFoundation extends PileAction("move-to-empty-foundation")
  case object MoveToEmptyFoundationAndReplace extends PileAction("move-to-empty-foundation-and-replace")
  case object LimitToTwoJacks extends PileAction("limit-to-two-jacks")
  override val values = findValues
}

case class TableauRules(
  name: String = "Tableau",
  setNumber: Int = 0,
  numPiles: Int = 7,
  cardsShown: Int = 0,
  initialCards: InitialCards = InitialCards.PileIndex,
  customInitialCards: Seq[String] = Seq.empty,
  uniqueRanks: Boolean = false,
  cardsFaceDown: TableauFaceDownCards = TableauFaceDownCards.AllButOne,
  suitMatchRuleForBuilding: SuitMatchRule = SuitMatchRule.AlternatingColors,
  rankMatchRuleForBuilding: RankMatchRule = RankMatchRule.Down,
  wrap: Boolean = false,
  suitMatchRuleForMovingStacks: SuitMatchRule = SuitMatchRule.AlternatingColors,
  rankMatchRuleForMovingStacks: RankMatchRule = RankMatchRule.Down,

  autoFillEmptyFrom: TableauAutoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
  emptyFilledWith: FillEmptyWith = FillEmptyWith.Any,

  mayMoveToNonEmptyFrom: Seq[PileSet.Behavior] = PileSet.Behavior.values,
  mayMoveToEmptyFrom: Seq[PileSet.Behavior] = PileSet.Behavior.values,

  maxCards: Int = 0,
  actionDuringDeal: PileAction = PileAction.None,
  actionAfterDeal: PileAction = PileAction.None,
  pilesWithLowCardsAtBottom: Int = 0
)
