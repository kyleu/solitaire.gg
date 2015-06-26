package models.game.rules.impl

import models.game.rules._

object PokerSquares extends GameRules(
  id = "pokersquares",
  title = "Poker Squares",
  description = "Make ten five-card poker hands from a 5x5 grid. Try for the high score!",
  layout = Some("5t|w"),
  victoryCondition = VictoryCondition.AllOnTableauSorted,

  tableaus = Seq(TableauRules(
    initialCards = InitialCards.Count(0),
    numPiles = 25,
    maxCards = 1,
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    rankMatchRuleForBuilding = RankMatchRule.Any
  )),
  waste = Some(WasteRules(
    cardsShown = 25,
    maxCards = Some(25)
  ))
)
