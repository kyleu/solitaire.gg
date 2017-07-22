package models.rules.impl

import models.rules._

object Thirteens extends GameRules(
  id = "thirteens",
  completed = false,
  title = "Thirteens",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thirteens.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/thirteens.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Helsinki.html.en"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
