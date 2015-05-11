// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau name (T0Nm): Left Tableau
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 3
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Tableau name (T1Nm): Right Tableau
 *   Tableau initial cards (T1d): 3 (3 cards)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 10
 *   Tableau suit match rule for building (T1s): 0 (May not build)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
object Friday extends GameRules(
  id = "friday",
  title = "Friday",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/friday.htm")),
  description = "A pair removal game with a two part tableau, only one of which is autofilled from the stock.",
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
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 10,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
