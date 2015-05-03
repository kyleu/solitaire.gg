// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object KingsdownEights extends GameRules(
  id = "kingsdowneights",
  title = "Kingsdown Eights",
  description = "This variation of ^tournament^ has a tableau where you can build by alternate color instead of cells.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Reserve,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(4)
    )
  ),
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = 4,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

