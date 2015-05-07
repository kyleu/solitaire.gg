// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   *S0cardsShown (S0cardsShown): 19
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Cards shown (T0cardsShown): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 0x0080
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 1
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Similar to (like): trustytwelve
 *   Number of decks (ndecks): 1 (1 deck)
 *   Enable super moves, whatever those are (supermoves): 1
 *   Victory condition (victory): 2 (No cards left in stock)
 */
object Bunker extends GameRules(
  id = "bunker",
  title = "Bunker",
  like = Some("trustytwelve"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bunker.htm")),
  description = "Build up regardless of suit to try to get all cards onto the tableau.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

