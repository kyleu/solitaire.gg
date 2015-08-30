// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 10
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 13
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object FourByTen extends GameRules(
  id = "fourbyten",
  completed = false,
  title = "Four by Ten",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/four_by_ten.htm")),
  description = "A ^freecell^ variation with lots of cells and not so many tableau piles.",
  layout = Some("f|c|t"),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(13),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 10
    )
  )
)