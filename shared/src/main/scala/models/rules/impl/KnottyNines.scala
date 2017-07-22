package models.rules.impl

import models.rules._

object KnottyNines extends GameRules(
  id = "knottynines",
  completed = false,
  title = "Knotty Nines",
  like = Some("trustytwelve"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/knotty_nines.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/knotty-nines.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/KnottyNines.htm"),
    Link("Antonia Hoyland", "www.allreadable.com/4c9d6pdH")
  ),
  layout = "s|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
