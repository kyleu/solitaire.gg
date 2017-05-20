package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Tournament extends GameRules(
  id = "tournament",
  completed = false,
  title = "Tournament",
  like = Some("cicely"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Tournament_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/tournament.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tournament.htm")
  ),
  layout = "sff|:c|::t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1), cardsDealt = StockCardsDealt.Count(4))),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits)
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Cell, PileSet.Behavior.Tableau)
    )
  ),
  cells = Some(CellRules(numPiles = 8, mayMoveToFrom = Seq(PileSet.Behavior.Tableau), initialCards = 8)),
  special = Some(SpecialRules(redealsAllowed = 2))
)
