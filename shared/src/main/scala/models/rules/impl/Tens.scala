package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 6 (Remove pairs adding to 10, or four 10s, Js, Qs, or Ks)
 *   Related games (related): nines, elevens
 */
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
