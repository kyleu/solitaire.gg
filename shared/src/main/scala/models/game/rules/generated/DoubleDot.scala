// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 1 (A)
 *   Foundation initial cards (F0d): 2 (2 cards)
 *   Maximum cards for foundation (F0m): 13
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   TODO (F0u): 1
 *   Foundation low rank (F1b): 2 (2)
 *   Foundation initial cards (F1d): 2 (2 cards)
 *   Maximum cards for foundation (F1m): 13
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   TODO (F1u): 1
 *   Foundation Sets (Fn): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 16 (Build down by 2)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau rank match rule for moving stacks (T0tr): 16 (Build down by 2)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Low card (lowpip): -1 (.)
 */
object DoubleDot extends GameRules(
  id = "doubledot",
  title = "Double Dot",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_dot.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_dot.htm")
  ),
  description = "An easy game where you build up by twos on the foundation, and down by twos on the tableau.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.Ace),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 13,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 13,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.DownBy2,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.DownBy2,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
