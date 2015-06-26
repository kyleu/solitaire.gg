// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Tableau name (T1Nm): Fan
 *   Tableau initial cards (T1d): 6 (6 cards)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 4
 *   Tableau rank match rule for building (T1r): 64 (Build equal)
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 */
object Willow extends GameRules(
  id = "willow",
  title = "Willow",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/willow.htm")),
  description = "A ^klondike^ variation with four fan piles where we can build with cards of equal rank. Invented by Thomas Warfield.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0)
    ),
    TableauRules(
      name = "Fan",
      setNumber = 1,
      numPiles = 4,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
