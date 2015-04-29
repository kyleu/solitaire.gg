// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object TvetesGrandfather extends GameRules(
  id = "tvetesgrandfather",
  title = "Tvete's Grandfather",
  description = "Paul Olav Tvete learned this game from his grandfather and included it in <a target=\"_blank\" href=\"\"http\"://www.kde.org/appli" +
  "cations/games/kpatience/\">KPatience</a>. It is a difficult game with an unusual tableau, ^yukon^-style stack moves, and two redea" +
  "ls.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("D", "DDUUUUU", "DDDDUUUUU", "DDDDDDUUUUU", "DDDDDUUUUU", "DDDUUUUU", "DUUUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

