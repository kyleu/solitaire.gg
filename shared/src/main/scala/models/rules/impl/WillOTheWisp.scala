// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Related games (related): fairmaids
 *   Right mouse interface function (rightfunc): 0
 */
object WillOTheWisp extends GameRules(
  id = "willothewisp",
  completed = false,
  title = "Will o the Wisp",
  related = Seq("fairmaids"),
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/will-o-the-wisp.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/will_o_the_wisp.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/WilloTheWisp.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/will_o_the_wisp.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/will_o_the_wisp.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/will_o_the_wisp.php"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Will_O_The_Wisp.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-will-o-the-wisp-solitaire-14333/")
  ),
  description = "A one-deck version of ^spider^, with a rectangular 7x3 tableau.",
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(3),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)