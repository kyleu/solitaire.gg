// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Ace Foundation
 *   Foundation low rank (F0b): 1 (A)
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 64 (Build equal)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Foundation name (F1Nm): Evens Foundation
 *   Foundation low rank (F1b): 2 (2)
 *   Maximum cards for foundation (F1m): 6
 *   Number of foundation piles (F1n): 8 (8 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Foundation name (F2Nm): Odds Foundation
 *   Foundation low rank (F2b): 3 (3)
 *   Maximum cards for foundation (F2m): 5
 *   Number of foundation piles (F2n): 8 (8 stacks)
 *   Foundation rank match rule (F2r): 256 (Build up by 2)
 *   Foundation Sets (Fn): 3
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   *T0an (T0an): 4
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Ranks in use (ranks): 4095
 */
object Carousel extends GameRules(
  id = "carousel",
  completed = true,
  title = "Carousel",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carousel.htm")),
  description = "A two-deck game with separate foundations for aces, evens and odds.",
  layout = Some("sw.f|f|f|t"),
  deckOptions = DeckOptions(
    numDecks = 2,
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.Ace)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      lowRank = FoundationLowRank.SpecificRank(Rank.Ace),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Equal,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Evens Foundation",
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Odds Foundation",
      setNumber = 2,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Three),
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 5,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
