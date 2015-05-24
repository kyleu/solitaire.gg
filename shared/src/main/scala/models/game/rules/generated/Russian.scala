// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DUUUUU DDUUUUU DDDUUUUU DDDDUUUUU DDDDDUUUUU DDDDDDUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): doublerussian, triplerussian, ukrainian, russiancell, odessa, tenacross
 */
object Russian extends GameRules(
  id = "russian",
  title = "Russian",
  related = Seq("doublerussian", "triplerussian", "ukrainian", "russiancell", "odessa", "tenacross"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/russian_solitaire.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Russian%20Solitaire.shtml"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/russian_solitaire.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RussianSolitaire.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/russian-solitaire.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/russian_solitaire.htm")
  ),
  description = "A harder variation of ^yukon^ where you must build down in the same suit instead of in alternate colors.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
