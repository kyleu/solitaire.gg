package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 7 (7 cards)
 *   Number of cells (C0n): 7
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   Number of waste piles (W0n): 0
 *   *W0s (W0s): true
 *   Similar to (like): kingalbert
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Muse extends GameRules(
  id = "muse",
  completed = true,
  title = "Muse",
  like = Some("kingalbert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/muse.htm")),
  layout = "::.f|:c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 9, cardsFaceDown = TableauFaceDownCards.Count(0))),
  cells = Some(CellRules(numPiles = 7, initialCards = 7))
)
