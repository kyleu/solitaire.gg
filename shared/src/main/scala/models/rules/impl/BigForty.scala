// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Maximum deals from stock (maxdeals): 0
 */
object BigForty extends GameRules(
  id = "bigforty",
  completed = true,
  title = "Big Forty",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_forty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BigForty.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/big-forty.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/big_forty.htm")
  ),
  description = "A one-deck variant of ^fortythieves^ that allows stack moves.",
  layout = Some("swf|t"),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)