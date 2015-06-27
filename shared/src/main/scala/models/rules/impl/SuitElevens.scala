// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 15
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): elevens
 *   Card removal method (pairs): 9 (Remove same suit pairs adding to 11 or J-Q-K)
 */
object SuitElevens extends GameRules(
  id = "suitelevens",
  title = "Suit Elevens",
  like = Some("elevens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/suit_elevens.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  description = "A variation of ^elevens^ where you can only remove sets of cards if they are all of the same suit.",
  cardRemovalMethod = CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK,
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
      numPiles = 15,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
