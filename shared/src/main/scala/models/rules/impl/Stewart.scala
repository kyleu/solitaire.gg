// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): martha
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Stewart extends GameRules(
  id = "stewart",
  completed = true,
  title = "Stewart",
  like = Some("martha"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stewart.htm")),
  description = "A more difficult variation of ^martha^ in which only single cards may be moved. It is similar to ^bakersdozen^.",
  layout = ":f|2t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)