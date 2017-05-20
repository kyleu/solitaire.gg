package models.rules.impl

import models.rules._

object TripleFourteens extends GameRules(
  id = "triplefourteens",
  completed = true,
  title = "Triple Fourteens",
  like = Some("fourteenout"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_fourteens.htm")),
  layout = "3tf",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  deckOptions = DeckOptions(numDecks = 3),
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 24,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
