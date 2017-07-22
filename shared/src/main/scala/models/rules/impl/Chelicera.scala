package models.rules.impl

import models.rules._

object Chelicera extends GameRules(
  id = "chelicera",
  completed = true,
  title = "Chelicera",
  like = Some("scorpion"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chelicera.htm")),
  layout = ":::s|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
