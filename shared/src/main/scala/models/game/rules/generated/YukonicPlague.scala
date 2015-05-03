// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object YukonicPlague extends GameRules(
  id = "yukonicplague",
  title = "Yukonic Plague",
  like = Some("yukon"),
  description = "A more difficult variation of ^yukon^ where many cards are buried in a reserve.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DDUU",
        "DDUUU",
        "DDUUUU",
        "DDUUUUU",
        "DDUUUUUU",
        "DDUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

