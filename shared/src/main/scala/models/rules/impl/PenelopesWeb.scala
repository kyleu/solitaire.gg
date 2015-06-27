// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): streetsandalleys
 *   Enable super moves, whatever those are (supermoves): 1
 */
object PenelopesWeb extends GameRules(
  id = "penelopesweb",
  title = "Penelope's Web",
  like = Some("streetsandalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/penelopes_web.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/penelope-web.htm")
  ),
  description = "A very difficult variation of ^beleagueredcastle^ where spaces can only be filled by kings.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
