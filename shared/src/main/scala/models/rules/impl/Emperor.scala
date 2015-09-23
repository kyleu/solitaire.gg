package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Similar to (like): rankandfile
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Emperor extends GameRules(
  id = "emperor",
  completed = false,
  title = "Emperor",
  like = Some("rankandfile"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Emperor_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/emperor.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/emperor.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/emperor.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/emperor.htm"),
    Link("Bicycle", "www.bicyclecards.ca/game-rules/emperor/184.php?page_id=32")
  ),
  description = "A more difficult version of ^rankandfile^ where only single cards can be moved..",
  layout = "swf|t",
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
