// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Bureau extends GameRules(
  id = "bureau",
  title = "Bureau",
  like = Some("athena"),
  description = "This game has rules similar to ^klondike^, except you build the foundation in alternate colors and cannot fill spaces in the table" +
  "au.",
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
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  ),
  complete = false
)

