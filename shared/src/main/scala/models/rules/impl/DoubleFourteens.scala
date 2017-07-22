package models.rules.impl

import models.rules._

object DoubleFourteens extends GameRules(
  id = "doublefourteens",
  completed = true,
  title = "Double Fourteens",
  like = Some("fourteenout"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_fourteens.htm")),
  layout = "2tf",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 18,
    initialCards = InitialCards.RestOfDeck,
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.None,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    emptyFilledWith = FillEmptyWith.None
  ))
)
