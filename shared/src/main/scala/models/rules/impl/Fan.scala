package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): boxfan, freefan, ceilingfan, midnightclover
 */
object Fan extends GameRules(
  id = "fan",
  completed = true,
  title = "Fan",
  related = Seq("boxfan", "freefan", "ceilingfan", "midnightclover"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fan.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fan.htm")
  ),
  layout = ".::f|2t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
