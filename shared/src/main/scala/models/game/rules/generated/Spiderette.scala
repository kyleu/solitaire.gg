// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): spidike
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object Spiderette extends GameRules(
  id = "spiderette",
  title = "Spiderette",
  like = Some("spider"),
  related = Seq("spidike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spiderette.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spiderette.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Spiderette.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/spiderette.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spiderette.html.en"),
    Link("Solitaire City", "www.solitairecity.com/iPhone/Spiderette.shtml")
  ),
  description = "A one-deck version of ^spider^, with a ^klondike^-style triangular tableau.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
