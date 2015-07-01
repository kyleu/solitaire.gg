package models.rules

import models.pile.Pile
import models.game.GameState

sealed trait VictoryCondition {
  def check(rules: GameRules, gs: GameState): Boolean
}

object VictoryCondition {
  val default = AllOnFoundation

  case object AllOnFoundation extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) = !gs.pileSets.exists(ps => ps.behavior != "foundation" && ps.piles.exists(_.cards.nonEmpty))
  }
  case object AllButFourCardsOnFoundation extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) = gs.pileSets.flatMap(ps => if (ps.behavior == "foundation") {
      Nil
    } else {
      ps.piles.flatMap(_.cards)
    }).length == 4
  }
  case object AllOnFoundationOrStock extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) = {
      !gs.pileSets.exists(ps => ps.behavior != "foundation" && ps.behavior != "stock" && ps.piles.exists(_.cards.nonEmpty))
    }
  }
  case object NoneInStock extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) =
      gs.pileSets.exists(ps => ps.behavior == "stock" && ps.piles.forall(_.cards.isEmpty)) &&
        !gs.pileSets.exists(ps => ps.behavior == "waste" && !ps.piles.forall(_.cards.isEmpty))
  }
  case object NoneInPyramid extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) = gs.pileSets.exists(ps => ps.behavior == "pyramid" && ps.piles.forall(_.cards.isEmpty))
  }
  case object AllOnTableauSorted extends VictoryCondition {
    override def check(rules: GameRules, gs: GameState) = {
      val tableauRules = rules.tableaus.toList match {
        case h :: Nil => h
        case _ => throw new IllegalStateException("Invalid number of tableau sets.")
      }
      def sorted(p: Pile) = p.isSorted(
        requireFaceUp = false,
        rmr = tableauRules.rankMatchRuleForBuilding,
        smr = tableauRules.suitMatchRuleForBuilding,
        lowRank = gs.deck.lowRank,
        wrap = tableauRules.wrap
      )
      val allTableauSorted = gs.pileSets.filter(_.behavior == "tableau").forall(ps => ps.piles.forall(sorted))
      val eligible = gs.pileSets.filterNot(ps => ps.behavior == "foundation" || ps.behavior == "tableau")
      val eligibleEmpty = eligible.forall(ps => ps.piles.forall(_.cards.isEmpty))
      allTableauSorted && eligibleEmpty
    }
  }
}
