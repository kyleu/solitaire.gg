package models.game

import models.RequestMessage
import models.pile.set.PileSet

class MoveHelper(gs: GameState, postMove: () => Unit) {
  protected[this] var moveCount = 0
  def getMoveCount = moveCount

  protected[this] var firstMoveMade: Option[Long] = None
  protected[this] var lastMoveMade: Option[Long] = None

  protected[this] val trackHistory = true
  val history = collection.mutable.ArrayBuffer.empty[(RequestMessage, Long)]

  def getFirstMove = firstMoveMade.getOrElse(0L)

  def registerMove(msg: RequestMessage) = {
    moveCount += 1
    if (firstMoveMade.isEmpty) {
      firstMoveMade = Some(System.currentTimeMillis)
    }
    lastMoveMade = Some(System.currentTimeMillis)
    if(trackHistory) {
      history += (msg -> System.currentTimeMillis)
    }
    postMove()
  }

  def possibleMoves() = {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val awesomeMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val boringMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gs.piles.foreach { source =>
      val sourceBehavior = source.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
      source.cards.zipWithIndex.foreach { c =>
        val cards = source.cards.drop(c._2)
        val remainingCards = source.cards.take(c._2)
        if (source.canDragFrom(cards, gs)) {
          gs.piles.filterNot(p => p.id == source.id).foreach { target =>
            val targetBehavior = target.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
            if (target.canDragTo(source, cards, gs)) {
              val move = PossibleMove(PossibleMove.Type.MoveCards, source.id, cards.map(_.id).toList, Some(target.id))
              if (sourceBehavior == PileSet.Behavior.Tableau && targetBehavior == PileSet.Behavior.Tableau && remainingCards.isEmpty && target.cards.isEmpty) {
                boringMoves += move
              } else if (targetBehavior == PileSet.Behavior.Foundation && sourceBehavior != PileSet.Behavior.Foundation) {
                awesomeMoves += move
              } else {
                ret += move
              }
            }
          }
        }
        if (source.canSelectCard(c._1, gs)) {
          ret += PossibleMove(PossibleMove.Type.SelectCard, source.id, Seq(c._1.id))
        }
      }
      if (source.canSelectPile(gs)) {
        ret += PossibleMove(PossibleMove.Type.SelectPile, source.id)
      }
    }
    (awesomeMoves ++ ret ++ boringMoves).toIndexedSeq
  }
}
