package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 */
object KlondikeTerritory extends GameRules(
  id = "klondiketerritory",
  completed = false,
  title = "Klondike Territory",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/klondike_territory.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/klondike_territory.html")
  ),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
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
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
