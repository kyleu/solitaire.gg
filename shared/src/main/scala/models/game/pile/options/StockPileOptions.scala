package models.game.pile.options

import models.game.pile.actions.{ SelectCardActions, SelectPileActions }
import models.game.pile.constraints.Constraints
import models.game.rules.{ StockRules, StockCardsDealt, StockDealTo }

object StockPileOptions {
  def apply(rules: StockRules, pileIdsByType: Map[String, Seq[String]]) = {
    val dragFromConstraint = if (rules.dealTo == StockDealTo.Manually) {
      Some(Constraints.topCardOnly)
    } else if (rules.dealTo == StockDealTo.WasteOrPairManually) {
      None // TODO Some(Constraints.topCardOnly)?
    } else {
      None
    }

    val selectCardConstraint = if (rules.dealTo == StockDealTo.TableauIfNoneEmpty) {
      Some(Constraints.allOf("all-non-empty-top-card", Constraints.allNonEmpty(pileIdsByType("tableaus")), Constraints.topCardOnly))
    } else {
      Some(Constraints.topCardOnly)
    }

    val selectPileConstraint = rules.maximumDeals match {
      case Some(1) => Some(Constraints.never)
      case Some(i) => Some(Constraints.empty) // TODO throw new NotImplementedError()
      case None => Some(Constraints.empty)
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

    val selectPileAction = if (redrawFrom.isEmpty) { None } else { Some(SelectPileActions.moveAllFrom(redrawFrom)) }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      direction = Some("r"),
      dragFromConstraint = dragFromConstraint,
      selectCardConstraint = selectCardConstraint,
      selectPileConstraint = selectPileConstraint,
      selectCardAction = selectCardAction,
      selectPileAction = selectPileAction
    )
  }
}
