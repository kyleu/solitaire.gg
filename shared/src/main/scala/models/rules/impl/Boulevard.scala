package models.rules.impl

import models.card.Rank
import models.rules._

object Boulevard extends GameRules(
  id = "boulevard",
  completed = true,
  title = "Boulevard",
  related = Seq("evenandodd"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/boulevard.htm")),
  layout = "sw.ff|rt",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 3, initialCards = 6, cardsFaceDown = -1))
)
