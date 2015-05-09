// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U UU UUU UUUU UUUUU UUUUUU UUUUUUU UUUUUUUU UUUUUUUU UUUUUUUU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): somerset
 *   Related games (related): morehead, usk
 */
object Morehead extends GameRules(
  id = "morehead",
  title = "Morehead",
  like = Some("somerset"),
  related = Seq("morehead", "usk"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/morehead.htm")),
  description = "A ^somerset^ variant where we build in different suits instead of alternate colors.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
