// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object FortyNine extends GameRules(
  id = "fortynine",
  title = "Forty-Nine",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_nine.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/forty_nine.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/forty-nine.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FortyNine.htm")
  ),
  description = "This ^fortyandeight^ variation has forty-nine cards in a seven by seven tableau. You build down regardless of suit, moving cards o" +
    "ne at a time.",
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
