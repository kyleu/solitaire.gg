// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Reserve initial cards (R0d): 21
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 191
 *   Tableau piles (T0n): 11
 *   May move to non-empty tableau from (T0o): 191
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Similar to (like): signora
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): empressofitaly
 */
object DoubleSignora extends GameRules(
  id = "doublesignora",
  title = "Double Signora",
  like = Some("signora"),
  related = Seq("empressofitaly"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_signora.htm")),
  description = "A four-deck version of ^signora^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 4,
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
      numPiles = 16,
      suitMatchRule = SuitMatchRule.AlternatingColors
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau"),
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 21,
      cardsFaceDown = 0
    )
  )
)
