// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TheWish extends GameRules(
  id = "thewish",
  title = "The Wish",
  description = "This easy pair-removal game uses a short deck and no stock, but is otherwise similar to ^doublets^.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  deckOptions = DeckOptions(
    ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

