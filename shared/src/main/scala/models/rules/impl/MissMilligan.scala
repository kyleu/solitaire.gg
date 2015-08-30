// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Create pocket when stock runs out (millres): 2 (For one stack of cards)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): imperialguards
 */
object MissMilligan extends GameRules(
  id = "missmilligan",
  completed = false,
  title = "Miss Milligan",
  related = Seq("imperialguards"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Miss_Milligan"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/miss_milligan.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/MissMilliganEasy.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/miss_milligan.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/miss_milligan.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/MissMilligan.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/miss_milligan.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/miss-milligan.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/miss_milligan.htm")
  ),
  description = "Starting with one card in each column, build sequences down by alternate color. Deal new cards from the deck into all columns. Whe" +
    "n the deck is empty, gain a reserve area you can waive a stack of cards into.",
  layout = Some("sf|:t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      createPocketWhenEmpty = true
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)