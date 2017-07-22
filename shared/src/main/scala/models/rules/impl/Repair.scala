package models.rules.impl

import models.rules._

object Repair extends GameRules(
  id = "repair",
  completed = false,
  title = "Repair",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/repair.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Repair.htm")
  ),
  layout = "f|c|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(initialCards = 4))
)
