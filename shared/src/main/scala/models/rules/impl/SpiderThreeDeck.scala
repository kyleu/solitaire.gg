// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): -2 (custom)
 *   Tableau cards face down (T0df): 100
 *   Custom initial cards (T0ds): DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDU DDDU DDDU DDDU DDDU DDDU
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): bigspider
 *   Number of decks (ndecks): 3 (3 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object SpiderThreeDeck extends GameRules(
  id = "spiderthreedeck",
  completed = false,
  title = "Spider Three Deck",
  like = Some("bigspider"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider_Three_Decks.html.en")),
  description = "This three-deck version of ^spider^ is a bit easier than ^bigspider^.",
  layout = Some("sf|t"),
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU"
      ),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)