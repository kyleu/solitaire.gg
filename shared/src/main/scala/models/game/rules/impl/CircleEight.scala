// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   May move to non-empty tableau from (T0o): 2 (waste)
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Touch interface function (touchfunc): 0x2|0x20
 *   Victory condition (victory): 2 (No cards left in stock)
 */
object CircleEight extends GameRules(
  id = "circleeight",
  title = "Circle Eight",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/circle_eight.htm")),
  description = "Move all cards to the tableau to win this game, but you can't move a card once it is on the tableau.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("waste")
    )
  )
)
