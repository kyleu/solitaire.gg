// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FlowerGarden extends GameRules(
  id = "flowergarden",
  title = "Flower Garden",
  description = "The six stacks of six cards in the tableau are called \"flower beds\". You can build down on them in any suit.  Instead of stock a" +
  "nd waste piles, you have a bouquet of 16 cards, any of which can be played at any time.",
  waste = Some(
    WasteRules(
      name = "Bouquet",
      cardsShown = 20
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Flower Beds",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

