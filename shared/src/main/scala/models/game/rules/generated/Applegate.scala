// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUU DDUUUUU DDUUUUU DDUUUUU DDUUUUU DDUUUUU DDUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 */
object Applegate extends GameRules(
  id = "applegate",
  title = "Applegate",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/applegate.htm")),
  description = "This game has similarities to both ^spider^ and ^yukon^, and may be an older version of ^scorpion^",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)
