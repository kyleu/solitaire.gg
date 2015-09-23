package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 1 (In same suit)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): 1 (stock)
 *   Tableau piles (T0n): 6
 *   May move to non-empty tableau from (T0o): 1 (stock)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): sirtommy
 */
object LadyBetty extends GameRules(
  id = "ladybetty",
  completed = false,
  title = "Lady Betty",
  like = Some("sirtommy"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_betty.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lady_betty.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lady_betty.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/lady_betty.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lady-betty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LadyBetty.htm")
  ),
  description = "This cousin of ^sirtommy^ requires you to build the foundation in suit, but gives you two extra tableau piles to work with.",
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
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
      name = "Reserve",
      numPiles = 6,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("stock"),
      mayMoveToEmptyFrom = Seq("stock")
    )
  )
)
