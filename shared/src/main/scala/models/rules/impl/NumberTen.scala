// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 2
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): rankandfile
 */
object NumberTen extends GameRules(
  id = "numberten",
  completed = false,
  title = "Number Ten",
  related = Seq("rankandfile"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/number_ten.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/number_ten.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/NumberTen.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/number-ten.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NumberTen.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/number_ten.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/number_ten.htm")
  ),
  description = "Like ^fortythieves^, but two cards in each tableau stack are dealt face down, we build in alternating colors, and can move stacks " +
    "as a whole.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2)
    )
  )
)