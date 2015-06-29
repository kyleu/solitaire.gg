package models.rules

case class SpecialRules(
  redealsAllowed: Int = 0,

  rotationsAllowed: Int = 0,
  rotationTopToBottom: Boolean = true,

  drawsAllowed: Int = 0,
  drawsAfterRedeals: Boolean = false
)
