package models.game

class MoveHelper(gs: GameState, postMove: () => Unit) {
  protected[this] var moveCount = 0
  protected[this] var firstMoveMade: Option[Long] = None
  protected[this] var lastMoveMade: Option[Long] = None

  def registerMove() = {
    moveCount += 1
    if (firstMoveMade.isEmpty) {
      firstMoveMade = Some(System.currentTimeMillis)
    }
    lastMoveMade = Some(System.currentTimeMillis)
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
              val move = PossibleMove(PossibleMove.Type.MoveCards, cards.map(_.id).toList, source.id, Some(target.id))
              if (sourceBehavior == "tableau" && targetBehavior == "tableau" && remainingCards.isEmpty && target.cards.isEmpty) {
                boringMoves += move
              } else if (targetBehavior == "foundation" && sourceBehavior != "foundation") {
                awesomeMoves += move
              } else {
                ret += move
              }
            }
          }
        }
        if (source.canSelectCard(c._1, gs)) {
          ret += PossibleMove(PossibleMove.Type.SelectCard, Seq(c._1.id), source.id)
        }
      }
      if (source.canSelectPile(gs)) {
        ret += PossibleMove(PossibleMove.Type.SelectPile, Nil, source.id)
      }
    }
    (awesomeMoves ++ ret ++ boringMoves).toIndexedSeq
  }
}
