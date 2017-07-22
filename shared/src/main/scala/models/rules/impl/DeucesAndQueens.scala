package models.rules.impl

import models.card.Rank
import models.rules._

object DeucesAndQueens extends GameRules(
  id = "deucesandqueens",
  completed = true,
  title = "Deuces and Queens",
  like = Some("acesandkings"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/deuces_and_queens.htm")),
  layout = "swff|:::r:t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Queen),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 3, initialCards = 13))
)
