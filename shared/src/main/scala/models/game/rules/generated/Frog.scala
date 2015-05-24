// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Reserve name (R0Nm): Frog
 *   Reserve initial cards (R0d): 13
 *   *R0dd (R0dd): 4
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): 1 (Stock)
 *   Tableau piles (T0n): 5
 *   May move to non-empty tableau from (T0o): 1 (Stock)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): fly
 */
object Frog extends GameRules(
  id = "frog",
  title = "Frog",
  related = Seq("fly"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Frog_(game)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/frog.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Frog.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/frog.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/frog.htm")
  ),
  description = "A relation of ^sirtommy^ with a reserve.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Frog",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)
