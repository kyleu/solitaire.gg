// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Red Foundation
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   TODO (F0u): 3
 *   Foundation name (F1Nm): Black Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): -1
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Foundation suit match rule (F1s): 4 (In alternating colors)
 *   TODO (F1u): 4
 *   Foundation Sets (Fn): 2
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): 1 (Stock)
 *   Tableau piles (T0n): 4
 *   May move to non-empty tableau from (T0o): 1 (Stock)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): sirtommy
 *   Related games (related): alternate, ladybetty
 */
object Alternate extends GameRules(
  id = "alternate",
  title = "Alternate",
  like = Some("sirtommy"),
  related = Seq("alternate", "ladybetty"),
  links = Seq(
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/alternate.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/alternate.php")
  ),
  description = "A variation of ^sirtommy^ where the foundations are built in alternate color, half upwards, half downwards.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCards = 2,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 2,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  )
)
