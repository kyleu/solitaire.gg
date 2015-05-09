// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Keep foundation off-screen (F0i): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUU UUUUUUUU UUUUUUUU UUUUUUU UUUUUU UUUUU UUUU UUU UU U
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object SimpleSimon extends GameRules(
  id = "simplesimon",
  title = "Simple Simon",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simple_simon.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Simple_Simon_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/simple_simon.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/simple-simon.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SimpleSimon.htm"),
    Link("Solitaire Whizz", "www.solitairewhizz.com/how-to-play/simple-simon.shtml"),
    Link("kPatience", "docs.kde.org/stable/en/kdegames/kpat/rules-specific.html#simple-simon")
  ),
  description = "Like a one-deck ^spider^ where all cards start face up in a triangular tableau and there are no further cards to deal.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
