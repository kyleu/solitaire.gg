// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation suit match rule (F0s): 3 (In same color)
 *   Initial card restriction (F0u): 1 (Unique colors)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation suit match rule (F1s): 3 (In same color)
 *   Initial card restriction (F1u): 1 (Unique colors)
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation add complete sequences only (F2cs): true
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation suit match rule (F2s): 4 (In alternating colors)
 *   Foundation Sets (Fn): 3
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): rougeetnoir
 */
object Diavolo extends GameRules(
  id = "diavolo",
  title = "Diavolo",
  related = Seq("rougeetnoir"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/easy_diavolo.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/diavolo.htm"),
    Link("Elton Gahr at HobbyHub", "www.hobbyhub360.com/index.php/view-article/1879401/")
  ),
  description = "A ^klondike^ variant with four foundation piles that are built one card at a time, while the other four need completed sequences.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
