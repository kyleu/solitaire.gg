// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Reserve initial cards (R0d): 7
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 10 (First stock then waste)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 7
 *   May move to non-empty tableau from (T0o): 191
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Low card (lowpip): -2 (?)
 */
object Balcony extends GameRules(
  id = "balcony",
  title = "Balcony",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/balcony.htm")),
  description = "Similar to ^canfield^, but foundations are built up in alternate colors, reserve cards can only be played to the foundation, and e" +
    "mpty spaces are autofilled from the stock.",
  deckOptions = DeckOptions(
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
      numPiles = 4,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 7,
      cardsFaceDown = 0
    )
  )
)
