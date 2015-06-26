// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUU UUUUU UUUUU UUUUU UUUUU UUUUU UUUU
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
object Links extends GameRules(
  id = "links",
  title = "Links",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/links.htm")),
  description = "A pair-removal game by Thomas Warfield, distantly related to ^golf^. Your priority should be to clear the cards in the stock.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
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
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
