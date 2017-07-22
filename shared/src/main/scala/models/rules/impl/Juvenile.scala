package models.rules.impl

import models.rules._

object Juvenile extends GameRules(
  id = "juvenile",
  completed = true,
  title = "Juvenile",
  like = Some("fourteenout"),
  links = Seq(
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/juvenile.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/juvenile.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/juvenile.php")
  ),
  layout = ":::.t|2tf",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 1,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 16,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
