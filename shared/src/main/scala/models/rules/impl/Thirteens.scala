// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
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
  description = "Remove pairs that add the thirteen. Entirely a game of luck.",
  layout = Some("sf|t"),
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
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