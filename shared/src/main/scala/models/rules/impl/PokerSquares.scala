package models.rules.impl

import models.rules._

object PokerSquares extends GameRules(
  id = "pokersquares",
  completed = false,
  title = "Poker Squares",
  description = "Make ten five-card poker hands from a 5x5 grid. Try for the high score!",
  layout = "5t|w",
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