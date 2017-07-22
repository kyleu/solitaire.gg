package models.rules.impl

import models.rules._

object EphemeralFreeCell extends GameRules(
  id = "ephemeralfreecell",
  completed = false,
  title = "Ephemeral FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html#Ephemeral")),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numEphemeral = 1))
)
