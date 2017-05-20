package models.rules

import enumeratum.values._
import models.game.GameState
import models.pile.Pile
import models.pile.set.PileSet

sealed abstract class VictoryCondition(val value: String) extends StringEnumEntry {
  def check(rules: GameRules, gs: GameState): Boolean
}

object VictoryCondition extends StringEnum[VictoryCondition] with StringCirceEnum[VictoryCondition] {
  val default = AllOnFoundation

  case object AllOnFoundation extends VictoryCondition("all-on-foundation") {
    override def check(rules: GameRules, gs: GameState) = !gs.pileSets.exists { ps =>
      ps.behavior != PileSet.Behavior.Foundation && ps.piles.exists(_.cards.nonEmpty)
    }
  }
  case object AllButFourCardsOnFoundation extends VictoryCondition("all-but-four-cards-on-foundation") {
    override def check(rules: GameRules, gs: GameState) = gs.pileSets.flatMap(ps => if (ps.behavior == PileSet.Behavior.Foundation) {
      Nil
    } else {
      ps.piles.flatMap(_.cards)
    }).length == 4
  }
  case object AllOnFoundationOrStock extends VictoryCondition("all-on-foundation-or-stock") {
    override def check(rules: GameRules, gs: GameState) = {
      !gs.pileSets.exists(ps => ps.behavior != PileSet.Behavior.Foundation && ps.behavior != PileSet.Behavior.Stock && ps.piles.exists(_.cards.nonEmpty))
    }
  }
  case object NoneInStock extends VictoryCondition("none-in-stock") {
    override def check(rules: GameRules, gs: GameState) =
      gs.pileSets.exists(ps => ps.behavior == PileSet.Behavior.Stock && ps.piles.forall(_.cards.isEmpty)) &&
        !gs.pileSets.exists(ps => ps.behavior == PileSet.Behavior.Waste && !ps.piles.forall(_.cards.isEmpty))
  }
  case object NoneInPyramid extends VictoryCondition("none-in-pyramid") {
    override def check(rules: GameRules, gs: GameState) = gs.pileSets.exists(ps => ps.behavior == PileSet.Behavior.Pyramid && ps.piles.forall(_.cards.isEmpty))
  }
  case object AllOnTableauSorted extends VictoryCondition("all-on-tableau-sorted") {
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
      val allTableauSorted = gs.pileSets.filter(_.behavior == PileSet.Behavior.Tableau).forall(ps => ps.piles.forall(sorted))
      val eligible = gs.pileSets.filterNot(ps => ps.behavior == PileSet.Behavior.Foundation || ps.behavior == PileSet.Behavior.Tableau)
      val eligibleEmpty = eligible.forall(ps => ps.piles.forall(_.cards.isEmpty))
      allTableauSorted && eligibleEmpty
    }
  }
  override val values = findValues
}
