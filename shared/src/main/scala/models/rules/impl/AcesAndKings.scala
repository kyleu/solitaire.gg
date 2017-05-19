package models.rules.impl

import models.rules._

object AcesAndKings extends GameRules(
  id = "acesandkings",
  completed = true,
  title = "Aces and Kings",
  related = Seq("doubleacesandkings", "aceyandkingsley", "deucesandqueens", "racingaces"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/aces_and_kings.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Aces_and_Kings_(solitaire)"),
    Link("MyPatience", "mypatience.net/Rules.aspx?gameId=33"),
    Link("eHow", "www.ehow.com/how_2106994_play-aces-kings-solitaire.html")
  ),
  layout = "swff|::r:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  reserves = Some(ReserveRules(numPiles = 2, initialCards = 13))
)
