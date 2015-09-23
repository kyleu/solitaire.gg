package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau cards face down (T0df): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 *   Related games (related): auntmary
 */
object Thoughtful extends GameRules(
  id = "thoughtful",
  completed = true,
  title = "Thoughtful",
  like = Some("klondike"),
  related = Seq("auntmary"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saratoga.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Klondike_(solitaire)")
  ),
  description = "^klondike^ with all cards in the tableau starting face up. Also known as \"Saratoga\".",
  layout = "swf|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
