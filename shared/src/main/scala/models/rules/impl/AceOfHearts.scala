package models.rules.impl

import models.card.Suit
import models.rules._

object AceOfHearts extends GameRules(
  id = "aceofhearts",
  completed = true,
  title = "Ace of Hearts",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ace_of_hearts.htm")),
  layout = "s::f|t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(
    FoundationRules(
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Spades)),
      suitMatchRule = SuitMatchRule.Any,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
