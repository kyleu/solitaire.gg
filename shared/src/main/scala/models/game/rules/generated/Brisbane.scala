// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUU DUUUUU DDUUUUU DDDUUUU DDDDUUUU DDDDDUUUU DDDDDDUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): yukon
 */
object Brisbane extends GameRules(
  id = "brisbane",
  title = "Brisbane",
  like = Some("yukon"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brisbane.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Brisbane%20Solitaire.shtml")
  ),
  description = "Just like ^yukon^, but the starting layout is a bit different and you build regardless of suit.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUU",
        "DDDDUUUU",
        "DDDDDUUUU",
        "DDDDDDUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
