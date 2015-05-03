// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BuffaloBill extends GameRules(
  id = "buffalobill",
  title = "Buffalo Bill",
  related = Seq("littlebillie"),
  description = "In this easy variation of ^littlebillie^, by David Parlett, there are more fans and the reserve cells start empty, but there are n" +
  "o redeals.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 26,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      name = "Reserve",
      pluralName = "Reserve",
      numPiles = 8
    )
  ),
  complete = false
)

