package models.rules.impl

import models.card.Rank
import models.rules._

object DoubleDot extends GameRules(
  id = "doubledot",
  completed = true,
  title = "Double Dot",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_dot.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_dot.htm")
  ),
  layout = "s:ff|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.Ace),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 13,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 13,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    rankMatchRuleForBuilding = RankMatchRule.DownBy2,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.DownBy2
  ))
)
