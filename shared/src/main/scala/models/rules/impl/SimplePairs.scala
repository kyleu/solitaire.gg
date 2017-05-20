package models.rules.impl

import models.rules._

object SimplePairs extends GameRules(
  id = "simplepairs",
  completed = false,
  title = "Simple Pairs",
  related = Seq("crisscross", "eighteens", "straightfifteens", "blockten", "patientpairs", "doubletcell", "doublets"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simple_pairs.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Jamestown.html.en"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/simple-pairs.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SimplePairs.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
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
