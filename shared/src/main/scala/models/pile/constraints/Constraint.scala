package models.pile.constraints

import models.pile.Pile
import models.rules.CardRemovalMethod
import models.game.{ Rank, Card, GameState }

case class Constraint(id: String, f: Constraint.Check, clientOptions: Option[Map[String, String]] = None)

object Constraint {
  type Check = (Pile, Pile, Seq[Card], GameState) => Boolean

  val always = Constraint("always", (src, tgt, cards, gameState) => true)
  val never = Constraint("never", (src, tgt, cards, gameState) => false)
  val empty = Constraint("empty", (src, tgt, cards, gameState) => tgt.cards.isEmpty)

  def allOf(id: String, constraints: Constraint*) = Constraint(id, (src, tgt, cards, gameState) => {
    val results = constraints.map(_.f(src, tgt, cards, gameState))
    !results.contains(false)
  })

  def finiteTimes(numTimes: Int) = {
    var counter = 0
    Constraint("finite-times", (src, tgt, cards, gameState) => {
      counter < numTimes
    }) -> (() => counter += 1)
  }

  val faceDown = Constraint("face-down", (src, tgt, cards, gameState) => !cards.exists(_.u))

  val topCardOnly = Constraint("top-card-only", (src, tgt, cards, gameState) => src.cards.lastOption == cards.headOption)

  def specificRanks(ranks: Seq[Rank]) = Constraint(s"any-${ranks.map(_.toChar).mkString}", (src, tgt, cards, gameState) => {
    !cards.exists(c => !ranks.contains(c.r))
  })

  def allNonEmpty(piles: Seq[String]) = Constraint("all-non-empty", (src, tgt, cards, gameState) => !piles.exists { p =>
    gameState.pilesById(p).cards.isEmpty
  })

  def pilesEmpty(piles: String*) = Constraint("piles-empty", (src, tgt, cards, gameState) => {
    !piles.exists(p => gameState.pilesById(p).cards.nonEmpty)
  }, Some(Map("piles" -> piles.mkString(","))))

  def forCardRemovalMethod(crm: CardRemovalMethod) = Constraint("card-removal", (src, tgt, cards, gameState) => {
    !cards.exists(c => !crm.canSelect(c))
  })
}
