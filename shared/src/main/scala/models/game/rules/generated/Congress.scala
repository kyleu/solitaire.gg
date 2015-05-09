// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): parliament, diplomat, dieppe
 */
object Congress extends GameRules(
  id = "congress",
  title = "Congress",
  related = Seq("parliament", "diplomat", "dieppe"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Congress_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/congress.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/congress.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/congress.html"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#congress")
  ),
  description = "This has similarities to ^fortyandeight^, but spaces in the tableau may only be filled from the waste. This gives the game a very " +
    "different feel.",
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
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation")
    )
  )
)
