// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CloverLeaf extends GameRules(
  id = "cloverleaf",
  title = "Clover Leaf",
  description = "An easy game invented by Thomas Warfield where you build up or down on the tableau, two foundation piles build up, and two build d" +
  "own.",
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCards = InitialCards.Count(2),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = InitialCards.Count(2),
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.KingsOrAces
    )
  ),
  complete = false
)

