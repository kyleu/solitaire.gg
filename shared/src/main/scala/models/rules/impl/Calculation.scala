package models.rules.impl

import models.rules._

object Calculation extends GameRules(
  id = "calculation",
  completed = true,
  title = "Calculation",
  aka = Map(
    "brokenintervals" -> "Broken Intervals",
    "hopscotch" -> "Hopscotch"
  ),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Calculation_(card_game)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/calculation.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/calculation.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/calculation.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Hopscotch.html.en")
  ),
  layout = "sf|t",
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.Ascending,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpByPileIndex,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("stock"),
      mayMoveToEmptyFrom = Seq("stock")
    )
  )
)
