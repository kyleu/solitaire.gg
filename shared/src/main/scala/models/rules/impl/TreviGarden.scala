package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Garden
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau cards face down (T0df): 102
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Fountain
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Similar to (like): stonewall
 */
object TreviGarden extends GameRules(
  id = "trevigarden",
  completed = false,
  title = "Trevi Garden",
  like = Some("stonewall"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trevi_garden.htm")),
  layout = "wf|c|t",
  waste = Some(
    WasteRules(
      name = "Fountain"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Garden",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2,
      initialCards = 2
    )
  )
)
