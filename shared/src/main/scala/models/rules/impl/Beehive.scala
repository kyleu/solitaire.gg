package models.rules.impl

import models.rules._

object Beehive extends GameRules(
  id = "beehive",
  completed = true,
  title = "Beehive",
  related = Seq("beehivegallery"),
  links = Seq(Link("Michael Keller's Description", "www.solitairelaboratory.com/buildingranks.html")),
  layout = "f|.:t:rsw",
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 13,
      lowRank = FoundationLowRank.Ascending,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      moveCompleteSequencesOnly = true,
      maxCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Equal
    )
  ),
  reserves = Some(ReserveRules(initialCards = 10, cardsFaceDown = -1))
)
