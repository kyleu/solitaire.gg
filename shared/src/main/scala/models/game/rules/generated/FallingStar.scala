// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Reserve initial cards (R0d): 11
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 191
 *   Tableau piles (T0n): 8
 *   May move to non-empty tableau from (T0o): 191
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Similar to (like): signora
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Custom suits (suits): 192
 */
object FallingStar extends GameRules(
  id = "fallingstar",
  title = "Falling Star",
  like = Some("signora"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/falling_star.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/falling_star.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/falling_star.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/falling-star.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FallingStar.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/falling_star.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/falling_star.htm")
  ),
  description = "A more difficult ^signora^ variation with one fewer tableau pile and a predetermined foundation base.",
  deckOptions = DeckOptions(
    numDecks = 2,
    suits = Seq(Suit.Horseshoes, Suit.Stars),
    lowRank = Rank.Unknown
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
      initialCards = 1,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 11,
      cardsFaceDown = 0
    )
  )
)
