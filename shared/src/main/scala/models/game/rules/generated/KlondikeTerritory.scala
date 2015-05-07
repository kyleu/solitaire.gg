// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

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
  title = "Klondike Territory",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/klondike_territory.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/klondike_territory.html")
  ),
  description = "A cross between ^flowergarden^ and ^klondike^, slightly more difficult than the similar Northwest Territory game.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

