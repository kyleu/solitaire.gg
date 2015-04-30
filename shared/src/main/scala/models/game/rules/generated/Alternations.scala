// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Alternations extends GameRules(
  id = "alternations",
  title = "Alternations",
  description = "A variation of ^interchange^ that has the same 7 by 7 tableau with alternate cards face down, but where you build in alternate col" +
  "ors.",
  deckOptions = DeckOptions(
    numDecks = 2
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
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

