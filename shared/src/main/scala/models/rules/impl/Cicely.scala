package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Cicely extends GameRules(
  id = "cicely",
  completed = false,
  title = "Cicely",
  like = Some("caprice"),
  related = Seq("tournament"),
  links = Seq(
    Link("Solsuite Solitaire", "www.solsuite.com/games/cicely.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cicely.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/cicely.htm")
  ),
  layout = "sff|c|t",
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
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Cell, PileSet.Behavior.Tableau)
    )
  ),
  cells = Some(CellRules(numPiles = 8, mayMoveToFrom = Seq(PileSet.Behavior.Tableau), initialCards = 8))
)
