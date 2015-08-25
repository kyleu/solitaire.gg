// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   *S0cardsShown (S0cardsShown): 19
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Cards shown (T0cardsShown): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): bunker, knottynines, sweetsixteen, upandup
 *   Victory condition (victory): 2 (No cards left in stock)
 */
object TrustyTwelve extends GameRules(
  id = "trustytwelve",
  completed = true,
  title = "Trusty Twelve",
  related = Seq("bunker", "knottynines", "sweetsixteen", "upandup"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trusty_twelve.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/trusty_twelve.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/trusty-twelve.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/TrustyTwelve.htm")
  ),
  description = "More luck than skill is needed to win this game of building sequences on the tableau.",
  layout = Some("s|2t"),
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.TableauEmpty,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
