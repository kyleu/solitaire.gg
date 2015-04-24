package models.game.rules

import models.game.GameState

sealed trait VictoryCondition {
  def check(gs: GameState): Boolean
}

object VictoryCondition {
  case object AllOnFoundation extends VictoryCondition {
    override def check(gs: GameState) = false
  }
  case object AllButFourCardsOnFoundation extends VictoryCondition {
    override def check(gs: GameState) = false
  }
  case object AllOnFoundationOrStock extends VictoryCondition {
    override def check(gs: GameState) = false
  }
  case object NoneInStock extends VictoryCondition {
    override def check(gs: GameState) = false
  }
  case object NoneInPyramid extends VictoryCondition {
    override def check(gs: GameState) = false
  }
  case object AllOnTableauSorted extends VictoryCondition {
    override def check(gs: GameState) = false
  }
}
