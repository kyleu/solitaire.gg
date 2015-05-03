// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Dimes extends GameRules(
  id = "dimes",
  title = "Dimes",
  like = Some("busyaces"),
  description = "A variation on ^deuces^ with fewer tableau piles.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Ten
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
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

