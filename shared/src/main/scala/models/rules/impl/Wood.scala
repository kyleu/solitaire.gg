// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Reserve initial cards (R0d): 10
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 2 (waste)
 *   Tableau piles (T0n): 8
 *   May move to non-empty tableau from (T0o): 191
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Wood extends GameRules(
  id = "wood",
  title = "Wood",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/wood.htm")),
  description = "A game where we build both the foundation and the tableau in alternate colors. The big problem is the ten-card reserve, which can " +
    "be played only to the foundation, which generally requires some advanced planning to achieve.",
  deckOptions = DeckOptions(
    numDecks = 2,
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau"),
      mayMoveToEmptyFrom = Seq("waste")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 10,
      cardsFaceDown = 0
    )
  )
)
