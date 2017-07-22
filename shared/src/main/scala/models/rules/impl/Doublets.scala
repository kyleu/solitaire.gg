package models.rules.impl

import models.rules._

object Doublets extends GameRules(
  id = "doublets",
  completed = true,
  title = "Doublets",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/doublets.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/doublets.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/doublets.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/doublets.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/doublet.html"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-doublets-14100/")
  ),
  layout = ".:s.:f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, lowRank = FoundationLowRank.AnyCard, maxCards = 0, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 12,
    initialCards = InitialCards.Count(4),
    suitMatchRuleForBuilding = SuitMatchRule.None,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
    emptyFilledWith = FillEmptyWith.None
  ))
)
