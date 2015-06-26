// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): luckypiles
 */
object LuckyThirteen extends GameRules(
  id = "luckythirteen",
  title = "Lucky Thirteen",
  related = Seq("luckypiles"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_thirteen.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lucky_thirteen.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LuckyThirteen.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lucky-thirteen.htm")
  ),
  description = "A rarely-winnable game with simple \"rules\": build down regardless of suit, no stack moves.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
