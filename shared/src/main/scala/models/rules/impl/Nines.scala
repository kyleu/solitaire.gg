package models.rules.impl

import models.rules._

object Nines extends GameRules(
  id = "nines",
  completed = true,
  title = "Nines",
  like = Some("tens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/nines.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/nines.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Nines.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "s.:f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
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
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
