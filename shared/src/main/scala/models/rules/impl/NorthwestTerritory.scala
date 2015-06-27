// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 */
object NorthwestTerritory extends GameRules(
  id = "northwestterritory",
  title = "Northwest Territory",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/northwest_territory.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/northwest_territory.html")
  ),
  description = "A cross between ^flowergarden^ and ^klondike^, slightly easier than the similar ^klondiketerritory^ game.",
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
      numPiles = 8,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
