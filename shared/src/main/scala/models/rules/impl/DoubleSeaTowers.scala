package models.rules.impl

import models.rules._

object DoubleSeaTowers extends GameRules(
  id = "doubleseatowers",
  completed = true,
  title = "Double Sea Towers",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_seatowers.htm")),
  layout = ".:f|:c|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(9),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 9, initialCards = 5))
)
