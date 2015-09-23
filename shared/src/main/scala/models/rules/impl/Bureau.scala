package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): athena
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Bureau extends GameRules(
  id = "bureau",
  completed = false,
  title = "Bureau",
  like = Some("athena"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bureau.htm")),
  description = "This game has rules similar to ^klondike^, except you build the foundation in alternate colors and cannot fill spaces in the table" +
    "au.",
  layout = "swf|t",
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
      suitMatchRule = SuitMatchRule.AlternatingColors,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
