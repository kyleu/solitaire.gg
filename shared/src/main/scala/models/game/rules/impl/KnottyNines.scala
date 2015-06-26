// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   *S0cardsShown (S0cardsShown): 19
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Cards shown (T0cardsShown): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): trustytwelve
 *   Number of decks (ndecks): 1 (1 deck)
 *   Victory condition (victory): 2 (No cards left in stock)
 */
object KnottyNines extends GameRules(
  id = "knottynines",
  title = "Knotty Nines",
  like = Some("trustytwelve"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/knotty_nines.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/knotty-nines.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/KnottyNines.htm"),
    Link("Antonia Hoyland", "www.allreadable.com/4c9d6pdH")
  ),
  description = "A more difficult variation of ^trustytwelve^.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
