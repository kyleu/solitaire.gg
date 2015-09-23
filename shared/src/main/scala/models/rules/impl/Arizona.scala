package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Related games (related): phoenix
 */
object Arizona extends GameRules(
  id = "arizona",
  completed = false,
  title = "Arizona",
  related = Seq("phoenix"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/arizona.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/arizona.htm")
  ),
  description = "An easier varition of ^wildflower^ where you can move sequences regardless of suit.",
  layout = "wf|t",
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
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
