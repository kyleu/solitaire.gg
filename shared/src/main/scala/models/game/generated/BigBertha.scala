// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object BigBertha extends GameRules(
  id = "bigbertha",
  title = "Big Bertha",
  description = "This two-deck version of ^kingalbert^ which has 14 reserve cards that are all playable, and a separate foundation pile that you can put all the kings on.",
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
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
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
// scalastyle:on

