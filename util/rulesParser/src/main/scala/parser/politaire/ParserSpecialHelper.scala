package parser.politaire

import models.rules.{ DealOrder, SpecialRules }

trait ParserSpecialHelper { this: GameRulesParser =>
  private[this] def getDealOrder(i: Int) = i match {
    case 9 => DealOrder.ColumnsLeftToRightTopToBottom
    case 16 => DealOrder.ColumnsLeftToRightTopToBottom
    case 1 => DealOrder.ColumnsLeftToRightBottomToTop
    case 11 => DealOrder.ColumnsRightToLeftTopToBottom
    case 3 => DealOrder.ColumnsRightToLeftBottomToTop
    case 0 => DealOrder.RowsLeftToRightTopToBottom
    case 2 => DealOrder.RowsLeftToRightBottomToTop
    case 6 => DealOrder.RowsZigZagBottomToTop
  }

  protected[this] def getSpecial = {
    val redeals = getInt("RDn")
    val rotations = getInt("nrot")
    val draws = getInt("ndraw")

    if(redeals > 0 || rotations > 0 || draws > 0) {
      Some(SpecialRules(
        redealsAllowed = redeals,
        pickupOrder = getDealOrder(getInt("RDp")),
        shuffleBeforeRedeal = getBoolean("RDs"),
        dealOrder = getDealOrder(getInt("RDd")),

        rotationsAllowed = rotations,
        rotationTopToBottom = getBoolean("toptobot"),

        drawsAllowed = draws,
        drawsAfterRedeals = getBoolean("drawrule")
      ))
    } else {
      None
    }
  }
}
