package models.game.rules

sealed trait DealOrder
object DealOrder {
  case object ColumnsLeftToRightTopToBottom extends DealOrder
  case object ColumnsLeftToRightBottomToTop extends DealOrder
  case object ColumnsRightToLeftTopToBottom extends DealOrder
  case object ColumnsRightToLeftBottomToTop extends DealOrder
  case object RowsLeftToRightTopToBottom extends DealOrder
  case object RowsLeftToRightBottomToTop extends DealOrder
  case object RowsZigZagBottomToTop extends DealOrder
}

case class SpecialRules(
  redealsAllowed: Int = 0,
  pickupOrder: DealOrder = DealOrder.ColumnsLeftToRightTopToBottom,
  shuffleBeforeRedeal: Boolean = false,
  dealOrder: DealOrder = DealOrder.ColumnsLeftToRightTopToBottom,

  rotationsAllowed: Int = 0,
  rotationTopToBottom: Boolean = true,

  drawsAllowed: Int = 0,
  drawsAfterRedeals: Boolean = true
)
