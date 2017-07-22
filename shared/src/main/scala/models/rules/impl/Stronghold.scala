package models.rules.impl

import models.rules._

object Stronghold extends GameRules(
  id = "stronghold",
  completed = false,
  title = "Stronghold",
  like = Some("streetsandalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stronghold.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stronghold.htm")
  ),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 1))
)
