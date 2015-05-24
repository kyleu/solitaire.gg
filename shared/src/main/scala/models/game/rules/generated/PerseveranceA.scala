// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 1 (Columns, left to right, bottom to top)
 *   Allowed pick ups/redeals (RDn): -1 (Unlimited)
 *   Pickup order (RDp): 1 (Columns, left to right, bottom to top)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): cruel
 *   Related games (related): perseveranceb
 *   Right mouse interface function (rightfunc): 0
 */
object PerseveranceA extends GameRules(
  id = "perseverancea",
  title = "Perseverance A",
  like = Some("cruel"),
  related = Seq("perseveranceb"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Perseverance_(solitaire)"),
    Link("An 1898 Description", "howtoplaysolitaire.blogspot.com/2010/06/perseverance-single-deck-solitaire-game.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/perseverance.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Perseverance.htm")
  ),
  description = "A variation of ^cruel^ where stacks may be moved.",
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
