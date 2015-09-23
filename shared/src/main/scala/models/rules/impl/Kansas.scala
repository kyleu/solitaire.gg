package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 3
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): rainbow
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 1 (1)
 */
object Kansas extends GameRules(
  id = "kansas",
  completed = false,
  title = "Kansas",
  like = Some("rainbow"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Kansas.html.en")),
  description = "Like ^rainbow^ this is a ^canfield^ variation where you build regardless of suit, but this is a bit more difficult because you onl" +
    "y have three tableau piles.",
  layout = "swf|r|t",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)
