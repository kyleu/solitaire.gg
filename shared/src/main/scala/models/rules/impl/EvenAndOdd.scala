package models.rules.impl

import models.card.Rank
import models.rules._

object EvenAndOdd extends GameRules(
  id = "evenandodd",
  completed = true,
  title = "Even and Odd",
  like = Some("boulevard"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/even-and-odd.htm")),
  layout = "sw.ff|rt",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 7
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
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
