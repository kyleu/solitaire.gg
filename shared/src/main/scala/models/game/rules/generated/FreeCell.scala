// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FreeCell extends GameRules(
  id = "freecell",
  title = "FreeCell",
  related = Seq("antares", "bigfreecell", "challengefreecell", "chinesefreecell", "doublefreecell", "ephemeralfreecell", "freecellduplex", "invertedfreecell", "selectivefreecell", "sevenbyfive", "sevenbyfour", "sixbyfour", "spidercells"),
  description = "Invented by Paul Alfille, made famous by Microsoft, this game provide four temporary storage cells that can be used to move cards " +
  "around.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

