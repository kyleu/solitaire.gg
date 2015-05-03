// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BigBertha extends GameRules(
  id = "bigbertha",
  title = "Big Bertha",
  description = "This two-deck version of ^kingalbert^ which has 14 reserve cards that are all playable, and a separate foundation pile that you ca" +
  "n put all the kings on.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Main Foundation",
      numPiles = 8,
      wrapFromKingToAce = true,
      maxCards = 12,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

