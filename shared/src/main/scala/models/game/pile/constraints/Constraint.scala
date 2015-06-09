package models.game.pile.constraints

import models.game.pile.Pile
import models.game.rules.CardRemovalMethod
import models.game.{ Rank, Card, GameState }

case class Constraint(id: String, f: Constraint.Check, clientOptions: Option[Map[String, String]] = None)

object Constraint {
  type Check = (Pile, Seq[Card], GameState) => Boolean

  val always = Constraint("always", (pile, cards, gameState) => true)
  val never = Constraint("never", (pile, cards, gameState) => false)
  val empty = Constraint("empty", (pile, cards, gameState) => pile.cards.isEmpty)

  def allOf(id: String, constraints: Constraint*) = Constraint(id, (pile, cards, gameState) => {
    val results = constraints.map(_.f(pile, cards, gameState))
    !results.contains(false)
  })

  def finiteTimes(numTimes: Int) = {
    var counter = 0
    Constraint("finite-times", (pile, cards, gameState) => {
      counter < numTimes
    }) -> (() => counter += 1)
  }

  val faceDown = Constraint("face-down", (pile, cards, gameState) => {
    !cards.exists(_.u)
  })

  val topCardOnly = Constraint("top-card-only", (pile, cards, gameState) => {
    pile.cards.lastOption == cards.headOption
  })

  def specificRanks(ranks: Seq[Rank]) = Constraint("any-" + ranks.map(_.toChar).mkString, (pile, cards, gameState) => {
    !cards.exists(c => !ranks.contains(c.r))
  })

  def allNonEmpty(piles: Seq[String]) = Constraint("all-non-empty", (pile, cards, gameState) => {
    !piles.exists { p =>
      gameState.pilesById(p).cards.isEmpty
    }
  })

  def pilesEmpty(piles: String*) = Constraint("piles-empty", (pile, cards, gameState) => {
    !piles.exists(p => gameState.pilesById(p).cards.nonEmpty)
  }, Some(Map("piles" -> piles.mkString(","))))

  def forCardRemovalMethod(crm: CardRemovalMethod) = Constraint("card-removal", (pile, cards, gameState) => {
    !cards.exists(c => !crm.canSelect(c))
  })
}
