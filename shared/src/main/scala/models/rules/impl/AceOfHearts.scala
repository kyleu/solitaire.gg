// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Initial card restriction (F0u): 8 (Spades)
 *   Foundation Sets (Fn): 1
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 */
object AceOfHearts extends GameRules(
  id = "aceofhearts",
  completed = true,
  title = "Ace of Hearts",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ace_of_hearts.htm")),
  description = "All cards must be built onto a single foundation pile in this Thomas Warfield invention.",
  layout = Some("s::f|t"),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Spades)),
      suitMatchRule = SuitMatchRule.Any,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
