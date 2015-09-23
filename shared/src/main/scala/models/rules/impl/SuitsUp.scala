package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 21 (Remove pairs of same suit)
 *   Victory condition (victory): 1 (All but 4 cards per deck on foundation)
 */
object SuitsUp extends GameRules(
  id = "suitsup",
  completed = false,
  title = "Suits Up",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/suits_up.htm")),
  description = "An easy game where you remove pairs of cards of the same suit, until only four cards are left.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameSuit,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
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
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
