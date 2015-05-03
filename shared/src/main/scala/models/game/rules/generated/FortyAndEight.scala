// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FortyAndEight extends GameRules(
  id = "fortyandeight",
  title = "Forty and Eight",
  related = Seq("lower48"),
  description = "Two decks, forty cards in the tableau, eight foundation piles, building down in the same suit. You can only move single cards. Oft" +
  "en it feels like nothing is happening for a long time, and then the game works out after all. A good game for making you feel smar" +
  "t.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

