package models.game.rules

import models.game.GameState

sealed trait VictoryCondition {
  def check(gs: GameState): Boolean
}

object VictoryCondition {
  case object AllOnFoundation extends VictoryCondition {
    override def check(gs: GameState) = !gs.pileSets.exists(ps => ps.behavior != "foundation" && ps.piles.exists(_.cards.nonEmpty))
  }
  case object AllButFourCardsOnFoundation extends VictoryCondition {
    override def check(gs: GameState) = gs.pileSets.flatMap(ps => if (ps.behavior == "foundation") {
      Nil
    } else {
      ps.piles.flatMap(_.cards)
    }).length == 4
  }
  case object AllOnFoundationOrStock extends VictoryCondition {
    override def check(gs: GameState) = !gs.pileSets.exists(ps => ps.behavior != "foundation" && ps.behavior != "stock" && ps.piles.exists(_.cards.nonEmpty))
  }
  case object NoneInStock extends VictoryCondition {
    override def check(gs: GameState) = gs.pileSets.exists(ps => ps.behavior == "stock" && ps.piles.forall(_.cards.isEmpty))
  }
  case object NoneInPyramid extends VictoryCondition {
    override def check(gs: GameState) = gs.pileSets.exists(ps => ps.behavior == "pyramid" && ps.piles.forall(_.cards.isEmpty))
  }
  case object AllOnTableauSorted extends VictoryCondition {
    override def check(gs: GameState) = !gs.pileSets.exists(ps => ps.behavior != "tableau" && ps.piles.exists(_.cards.nonEmpty)) // TODO For reals.
  }
}
