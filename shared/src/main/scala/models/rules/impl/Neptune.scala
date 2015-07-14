// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Suit
import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Number of decks (ndecks): 4 (4 decks)
 *   Card removal method (pairs): 16 (Remove pairs with consecutive ranks)
 *   Related games (related): shuffle
 *   Custom suits (suits): 512 (M)
 *   Victory condition (victory): 1 (All but 4 cards per deck on foundation)
 */
object Neptune extends GameRules(
  id = "neptune",
  title = "Neptune",
  related = Seq("shuffle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/neptune.htm")),
  description = "A game where you remove pairs of consecutive cards.",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveRankPairs,
  deckOptions = DeckOptions(
    numDecks = 4,
    suits = Seq(Suit.Moons)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
