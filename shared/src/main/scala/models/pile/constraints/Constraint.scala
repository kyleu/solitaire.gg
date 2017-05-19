package models.pile.constraints

import models.card.{Card, Rank}
import models.game.GameState
import models.pile.Pile
import models.rules.CardRemovalMethod

case class Constraint(id: String, f: Constraint.Check, clientOptions: Option[Map[String, String]] = None)

object Constraint {
  type Check = (Pile, Pile, Seq[Card], GameState) => Boolean

  val always = Constraint("always", (_, _, _, _) => true)
  val never = Constraint("never", (_, _, _, _) => false)
  val empty = Constraint("empty", (_, tgt, _, _) => tgt.cards.isEmpty)

  def allOf(id: String, constraints: Constraint*) = Constraint(id, (src, tgt, cards, gameState) => {
    val results = constraints.map(_.f(src, tgt, cards, gameState))
    !results.contains(false)
  })

  def finiteTimes(numTimes: Int) = Constraint("finite-times", (_, _, _, gameState) => {
    gameState.stockCounter < numTimes
  })

  val faceDown = Constraint("face-down", (_, _, cards, _) => !cards.exists(_.u))

  val topCardOnly = Constraint("top-card-only", (src, _, cards, _) => src.cards.lastOption == cards.headOption)

  def specificRanks(ranks: Seq[Rank]) = Constraint(s"any-${ranks.map(_.value).mkString}", (_, _, cards, _) => {
    !cards.exists(c => !ranks.contains(c.r))
  })

  def allNonEmpty(piles: Seq[String]) = Constraint("all-non-empty", (_, _, _, gameState) => !piles.exists { p =>
    gameState.pilesById(p).cards.isEmpty
  })

  def pilesEmpty(piles: String*) = Constraint("piles-empty", (_, _, _, gameState) => {
    !piles.exists(p => gameState.pilesById(p).cards.nonEmpty)
  }, Some(Map("piles" -> piles.mkString(","))))

  def forCardRemovalMethod(crm: CardRemovalMethod) = Constraint("card-removal", (_, _, cards, _) => {
    !cards.exists(c => !crm.canSelect(c))
  })
}
