// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): waningmoon
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): lucas
 *   Custom suits (suits): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object ThirtyNineSteps extends GameRules(
  id = "thirtyninesteps",
  title = "Thirty Nine Steps",
  like = Some("waningmoon"),
  related = Seq("lucas"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thirty_nine_steps.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thirty-nine-steps.htm")
  ),
  description = "^waningmoon^ with fewer cards in the initial tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
