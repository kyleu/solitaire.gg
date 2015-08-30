// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 */
object Lanes extends GameRules(
  id = "lanes",
  completed = true,
  title = "Lanes",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lanes.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Lanes.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lanes.htm")
  ),
  description = "A six-by-three tableau played much like ^klondike^, but you can't move stacks.",
  layout = "swf|.t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
