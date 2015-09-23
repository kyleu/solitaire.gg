package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau action during deal (T0dd): 3 (Move cards to foundations)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): citadel
 *   Enable super moves, whatever those are (supermoves): 0
 */
object ExiledKings extends GameRules(
  id = "exiledkings",
  completed = false,
  title = "Exiled Kings",
  like = Some("citadel"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/exiled_kings.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/exiled-kings.htm")
  ),
  description = "A more difficult variation of ^citadel^ where spaces can only be filled by kings.",
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
