package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau cards face down (T0df): 101
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Alternations extends GameRules(
  id = "alternations",
  completed = true,
  title = "Alternations",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alternations.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Alternation_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/alternation.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/alternations.htm"),
    Link("Solitaire City", "www.solitairecity.com/Alternations.shtml")
  ),
  layout = "swf|::t",
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
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered
    )
  )
)
