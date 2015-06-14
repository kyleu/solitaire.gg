package models.game.pile.options

import models.game.pile.constraints.Constraint
import models.game.rules._
import models.game.{ Card, Rank }

trait TableauPileOptionHelper {
  protected[this] def dragFrom(rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank, wrapFromKingToAce: Boolean) = {
    if (rmr == RankMatchRule.None || smr == SuitMatchRule.None) {
      Constraint.topCardOnly
    } else {
      Constraint("sequence", (src, tgt, cards, gameState) => {
        if (cards.exists(!_.u)) {
          false
        } else {
          var valid = true
          var lastCard: Option[Card] = None
          for (c <- cards) {
            if (valid) {
              lastCard.foreach { lc =>
                if (!rmr.check(lc.r, c.r, lowRank, wrapFromKingToAce)) {
                  valid = false
                } else if (!smr.check(lc.s, c.s)) {
                  valid = false
                }
              }
            }
            lastCard = Some(c)
          }
          valid
        }
      }, Some(Map("r" -> rmr.toString, "s" -> smr.toString, "lr" -> lowRank.value.toString)))
    }
  }

  protected[this] def dragTo(
    crm: CardRemovalMethod,
    rmr: RankMatchRule,
    smr: SuitMatchRule,
    lowRank: Rank,
    emptyPileRanks: Seq[Rank],
    maxCards: Int,
    wrapFromKingToAce: Boolean
  ) = {
    Constraint("tableau", (src, tgt, cards, gameState) => {
      if (maxCards > 0 && tgt.cards.length + cards.length > maxCards) {
        false
      } else {
        if (tgt.cards.isEmpty) {
          emptyPileRanks.length match {
            case 0 => false
            case _ => cards.exists(c => emptyPileRanks.contains(c.r))
          }
        } else {
          val firstDraggedCard = cards.headOption.getOrElse(throw new IllegalStateException())
          val topCard = tgt.cards.lastOption.getOrElse(throw new IllegalStateException())
          if (!topCard.u) {
            false
          } else {
            crm match {
              case CardRemovalMethod.BuildSequencesOnFoundation => if (smr.check(topCard.s, firstDraggedCard.s)) {
                rmr.check(topCard.r, firstDraggedCard.r, lowRank, wrapFromKingToAce)
              } else {
                false
              }
              case CardRemovalMethod.StackSameRankOrSuitInWaste => throw new NotImplementedError()
              case _ => crm.canRemove(topCard, firstDraggedCard)
            }
          }
        }
      }
    })
  }
}
