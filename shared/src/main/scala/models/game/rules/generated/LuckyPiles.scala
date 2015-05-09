// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 13
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): luckythirteen
 */
object LuckyPiles extends GameRules(
  id = "luckypiles",
  title = "Lucky Piles",
  like = Some("luckythirteen"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_piles.htm")),
  description = "A vastly easier varition of ^luckythirteen^ in which you can build up or down.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)
