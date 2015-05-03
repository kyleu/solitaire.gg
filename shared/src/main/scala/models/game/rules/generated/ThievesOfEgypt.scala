// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ThievesOfEgypt extends GameRules(
  id = "thievesofegypt",
  title = "Thieves of Egypt",
  description = "A variant of ^fortythieves^ with a pyramid-shaped tableau.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UUU",
        "UUUUU",
        "UUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUU",
        "UUUUUU",
        "UUUU",
        "UU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

