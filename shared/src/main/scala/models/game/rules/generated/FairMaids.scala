// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 4 (To all non-empty tableau piles)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): willothewisp
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 *   *vsuit (vsuit): 4
 */
object FairMaids extends GameRules(
  id = "fairmaids",
  title = "Fair Maids",
  like = Some("willothewisp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fair_maids.htm")),
  description = "A variation of ^willothewisp^ where we build in alternate colors.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauNonEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
