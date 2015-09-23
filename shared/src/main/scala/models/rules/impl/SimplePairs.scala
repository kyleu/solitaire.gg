package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 1 (Remove pairs of same rank)
 *   Related games (related): crisscross, eighteens, straightfifteens, blockten, patientpairs, doubletcell, do...
 */
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
  description = "A game where you remove pairs of cards of the same rank. Bring your luck, not your brain, to this game.",
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
