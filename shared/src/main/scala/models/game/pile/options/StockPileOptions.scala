package models.game.pile.options

import models.game.pile.actions.{ SelectCardActions, SelectPileActions }
import models.game.pile.constraints.Constraint
import models.game.rules.{ StockRules, StockCardsDealt, StockDealTo }

object StockPileOptions {
  def apply(rules: StockRules, pileIdsByType: Map[String, Seq[String]]) = {
    val dragFromConstraint = if (rules.dealTo == StockDealTo.Manually) {
      Some(Constraint.topCardOnly)
    } else if (rules.dealTo == StockDealTo.WasteOrPairManually) {
      Some(Constraint.topCardOnly)
    } else {
      None
    }

    val selectCardConstraint = if (rules.dealTo == StockDealTo.TableauIfNoneEmpty) {
      Some(Constraint.allOf("all-non-empty-top-card", Constraint.allNonEmpty(pileIdsByType("tableaus")), Constraint.topCardOnly))
    } else {
      Some(Constraint.topCardOnly)
    }

    val selectPileConstraint: (Constraint, () => Unit) = rules.maximumDeals match {
      case Some(1) => Constraint.never -> (() => Unit)
      case Some(i) => Constraint.finiteTimes(i - 1)
      case None => Constraint.empty -> (() => Unit)
    }

    val cardsToDraw = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => i
      case StockCardsDealt.FewerEachTime => 3 // TODO throw new NotImplementedError()
    }

    val drawTo = rules.dealTo match {
      case StockDealTo.Waste => pileIdsByType("waste")
      case StockDealTo.WasteOrPairManually => pileIdsByType("waste")
      case StockDealTo.Reserve => pileIdsByType("reserves")
      case StockDealTo.Foundation => pileIdsByType("foundations")
      case StockDealTo.Tableau => pileIdsByType("tableaus")
      case StockDealTo.TableauFirstSet => pileIdsByType("tableaus").filter(_.startsWith("tableau-"))
      case StockDealTo.TableauIfNoneEmpty => pileIdsByType("tableaus")
      case StockDealTo.TableauNonEmpty => pileIdsByType("tableaus")
      case StockDealTo.Manually => Nil
      case StockDealTo.Never => Nil
    }

    val redrawFrom = rules.maximumDeals match {
      case Some(1) => Nil
      case _ => drawTo
    }

    val selectCardAction = if (drawTo.nonEmpty) {
      if (rules.dealTo == StockDealTo.Manually) {
        None
      } else if (rules.dealTo == StockDealTo.TableauNonEmpty) {
        Some(SelectCardActions.drawToNonEmptyPiles(cardsToDraw, drawTo, Some(true)))
      } else {
        Some(SelectCardActions.drawToPiles(cardsToDraw, drawTo, Some(true)))
      }
    } else {
      None
    }

    val selectPileAction = if (redrawFrom.isEmpty) {
      None
    } else {
      Some(SelectPileActions.moveAllFrom(redrawFrom, selectPileConstraint._2))
    }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      direction = Some("r"),
      dragFromConstraint = dragFromConstraint,
      selectCardConstraint = selectCardConstraint,
      selectPileConstraint = Some(selectPileConstraint._1),
      selectCardAction = selectCardAction,
      selectPileAction = selectPileAction
    )
  }
}
