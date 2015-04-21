package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object BrownRecluse extends GameRules(
  id = "brownrecluse",
  title = "Brown Recluse",
  description = "This ^spider^ variant by Thomas Warfield has a stock and a waste pile. The tableau starts with just one card in each column, but spaces are autofilled from the waste or stock.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions = DeckOptions(
    numDecks = 2,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs),
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace),
    lowRank = Some(Rank.Ace)
  ),
  stock = Some(
    Stock(
      name = "Stock",
      dealTo = StockDealTo.Waste,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  waste = Some(
    WasteSet(

      name = "Waste",
      numPiles = 1,
      playableCards = WastePlayableCards.TopCardOnly
    )
  ),
  foundations = Nil,
  tableaus = Nil,
  cells = None,
  reserves = None,
  pyramids = Nil
)
// scalastyle:on

