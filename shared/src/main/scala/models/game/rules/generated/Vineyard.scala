// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Related games (related): tenbyone, winery
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Vineyard extends GameRules(
  id = "vineyard",
  title = "Vineyard",
  related = Seq("tenbyone", "winery"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/vineyard.htm")),
  description = "A difficult variation of ^bakersdozen^ invented by Peter Voke.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
