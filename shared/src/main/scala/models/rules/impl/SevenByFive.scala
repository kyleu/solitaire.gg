package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 5
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object SevenByFive extends GameRules(
  id = "sevenbyfive",
  completed = false,
  title = "Seven by Five",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/seven_by_five.htm")),
  description = "A ^freecell^ variant with one fewer tableau column and more more cell.",
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 5
    )
  )
)
