package models.game.rules

sealed trait VictoryCondition
object VictoryCondition {
  case object AllOnFoundation extends VictoryCondition
  case object AllButFourCardsOnFoundation extends VictoryCondition
  case object AllOnFoundationOrStock extends VictoryCondition
  case object NoneInStock extends VictoryCondition
  case object NoneInPyramid extends VictoryCondition
  case object AllOnTableauSorted extends VictoryCondition
}
