package models.rules.impl

import models.rules._

object MilliganYukon extends GameRules(
  id = "milliganyukon",
  completed = true,
  title = "Milligan Yukon",
  like = Some("milliganharp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/milligan_yukon.htm")),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
