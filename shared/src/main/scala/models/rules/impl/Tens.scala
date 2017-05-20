package models.rules.impl

import models.rules._

object Tens extends GameRules(
  id = "tens",
  completed = false,
  title = "Tens",
  related = Seq("nines", "elevens"),
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/Tens.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/tens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Tens.htm"),
    Link("tiffehr on eHow.com", "www.ehow.com/how_2000644_play-tens-solitaire.html"),
    Link("Erik Arneson on About.com", "boardgames.about.com/od/solitaire/a/tens.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
