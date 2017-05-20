package models.rules.impl

import models.card.Rank
import models.rules._

object Penta extends GameRules(
  id = "penta",
  completed = true,
  title = "Penta",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/penta.htm")),
  layout = ":::.sw|f:f|:t:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Left Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
