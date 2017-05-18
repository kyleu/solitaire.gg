package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Pantagruel extends GameRules(
  id = "pantagruel",
  completed = true,
  title = "Pantagruel",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pantagruel.htm")),
  layout = "swf|:t",
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
      numPiles = 9
    )
  )
)
