package parser

import models.game.rules._

object ScalaSpecialExporter {
  private[this] val defaults = SpecialRules()

  def exportSpecial(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.special match {
      case Some(s) =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(s.redealsAllowed != defaults.redealsAllowed) {
          props += "      redealsAllowed = " + s.redealsAllowed
        }
        if(s.pickupOrder != defaults.pickupOrder) {
          props += "      pickupOrder = DealOrder." + s.pickupOrder
        }
        if(s.shuffleBeforeRedeal != defaults.shuffleBeforeRedeal) {
          props += "      shuffleBeforeRedeal = " + s.shuffleBeforeRedeal
        }
        if(s.dealOrder != defaults.dealOrder) {
          props += "      dealOrder = DealOrder." + s.dealOrder
        }
        if(s.rotationsAllowed != defaults.rotationsAllowed) {
          props += "      rotationsAllowed = " + s.rotationsAllowed
        }
        if(s.rotationTopToBottom != defaults.rotationTopToBottom) {
          props += "      rotationTopToBottom = " + s.rotationTopToBottom
        }
        if(s.drawsAllowed != defaults.drawsAllowed) {
          props += "      drawsAllowed = " + s.drawsAllowed
        }
        if(s.drawsAfterRedeals != defaults.drawsAfterRedeals) {
          props += "      drawsAfterRedeals = " + s.pickupOrder
        }

        if(props.isEmpty) {
          add("  special = Some(SpecialRules()),")
        } else {
          add("  special = Some(")
          add("    SpecialRules(\n")
          add(props.mkString(",\n"))
          add("    )")
          add("  ),")
        }
      case None =>  // no op
    }
  }
}
