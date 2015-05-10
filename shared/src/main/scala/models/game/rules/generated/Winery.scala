// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): vineyard
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Winery extends GameRules(
  id = "winery",
  title = "Winery",
  like = Some("vineyard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/winery.htm")),
  description = "A version of ^vineyard^ with cells added.",
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2,
      initialCards = 2
    )
  )
)
