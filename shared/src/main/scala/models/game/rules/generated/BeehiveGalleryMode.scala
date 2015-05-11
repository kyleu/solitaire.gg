// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 23 (Ace, Two, Thre...)
 *   Foundation add complete sequences only (F0cs): true
 *   Maximum cards for foundation (F0m): 4
 *   Number of foundation piles (F0n): 13
 *   Foundation rank match rule (F0r): 64 (Build equal)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Reserve initial cards (R0d): 10
 *   Number of reserve piles (R0n): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau rank match rule for building (T0r): 64 (Build equal)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau rank match rule for moving stacks (T0tr): 64 (Build equal)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Waste name (W0Nm): Gallery
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   *gallery (gallery): 1
 *   Similar to (like): beehive
 *   Maximum deals from stock (maxdeals): 0
 */
object BeehiveGalleryMode extends GameRules(
  id = "beehivegallery",
  title = "Beehive (Gallery Mode)",
  like = Some("beehive"),
  description = "This is just ^beehive^ with a different user \"interface\": all the cards that would normally start in the stock are fanned out fa" +
    "ce up, with the ones that would normally be playable if you were going through the stock three at a time automatically raised up t" +
    "o indicate that they are playable.",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 13,
      lowRank = FoundationLowRank.Ascending,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      maxCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Equal
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 10,
      cardsFaceDown = 100
    )
  )
)
