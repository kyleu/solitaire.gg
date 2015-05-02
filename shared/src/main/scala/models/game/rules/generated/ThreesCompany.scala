// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ThreesCompany extends GameRules(
  id = "threescompany",
  title = "Three's Company",
  description = "A rather difficult variation of ^deuces^ or ^busyaces^ with still fewer tableau piles but stack moves are allowed. Invented by Tho" +
  "mas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Three)
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
      initialCards = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

