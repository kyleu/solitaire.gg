// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Steps extends GameRules(
  id = "steps",
  completed = false,
  title = "Steps",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/steps.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/steps.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Steps.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/steps.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Steps.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/steps.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/steps.htm")
  ),
  description = "A two-deck version of ^klondike^.",
  layout = Some("swf|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)