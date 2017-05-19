package models.rules.impl

import models.rules._

object DoubleAcesAndKings extends GameRules(
  id = "doubleacesandkings",
  completed = true,
  title = "Double Aces and Kings",
  like = Some("acesandkings"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_aces_and_kings.htm")),
  layout = "swf|:::f|:.t|:::.r",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  reserves = Some(ReserveRules(numPiles = 4, initialCards = 13))
)
