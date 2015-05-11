// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation Sets (Fn): 0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object SimonSays extends GameRules(
  id = "simonsays",
  title = "Simon Says",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simon_says.htm")),
  description = "A blend of ^simplesimon^ with ^freecell^ invented by Thomas Warfield.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)
