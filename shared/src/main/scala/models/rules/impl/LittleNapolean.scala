// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): mcclellan
 */
object LittleNapolean extends GameRules(
  id = "littlenapoleon",
  completed = false,
  title = "Little Napolean",
  like = Some("fortythieves"),
  related = Seq("mcclellan"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_napoleon.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleNapoleon.htm"),
    Link("Super Solitaire", "supersolitaire.weisswo.com/Games+Rules/Entries/2009/2/15_Little_Napoleon_Solitaire.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/little_napoleon.htm")
  ),
  description = "A ^fortythieves^ variant that shows some ^spider^ influences, because you can build regardless of suit, but only move same-suit se" +
    "quences.",
  layout = Some("swf|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)