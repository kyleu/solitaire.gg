package models.rules.impl

import models.rules._

object Queenie extends GameRules(
  id = "queenie",
  completed = true,
  title = "Queenie",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/queenie.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Queenie.html"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/queenie.html")
  ),
  layout = "s:f|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
