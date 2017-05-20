package models.rules.impl

import models.rules._

object Tripleharp extends GameRules(
  id = "tripleharp",
  completed = true,
  title = "Tripleharp",
  like = Some("harp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_harp.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      maximumDeals = Some(4)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
