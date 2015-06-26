// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   Number of waste piles (W0n): 1
 *   *W0s (W0s): true
 *   Similar to (like): fortress
 *   Enable super moves, whatever those are (supermoves): 1
 */
object BeleagueredFortress extends GameRules(
  id = "beleagueredfortress",
  title = "Beleaguered Fortress",
  like = Some("fortress"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/beleaguered-castle-2.htm")),
  description = "A variation of ^fortress^ with a twelve-card reserve from which all cards are playable.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
