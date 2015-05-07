// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau cards face down (T0df): 102
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Related games (related): trevigarden
 */
object Stonewall extends GameRules(
  id = "stonewall",
  title = "Stonewall",
  related = Seq("trevigarden"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Stonewall_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stonewall.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/stonewall.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stonewall.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/stonewall.php")
  ),
  description = "Similar to ^flowergarden^, except some cards start face down, you must build in alternate colors, and you can move sequences.  A h" +
    "ard game to win.",
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
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

