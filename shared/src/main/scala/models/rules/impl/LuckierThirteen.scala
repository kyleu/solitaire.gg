// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object LuckierThirteen extends GameRules(
  id = "luckierthirteen",
  completed = true,
  title = "Luckier Thirteen",
  aka = Map("thirteenbyzero" -> "Thirteen by Zero"),
  description = "An easier version of ^luckythirteen^, or a cell-free version of ^freecell^. Also known as \"Thirteen by Zero\".",
  layout = Some("::::.f|t"),
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
