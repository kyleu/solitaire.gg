package models.rules.impl

import models.rules._

object Cromwell extends GameRules(
  id = "cromwell",
  completed = false,
  title = "Cromwell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cromwell.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/cromwell.php")
  ),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 26,
    initialCards = InitialCards.Count(4),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
    emptyFilledWith = FillEmptyWith.None
  )),
  special = Some(SpecialRules(drawsAllowed = 1))
)
