package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object KnottyNines extends GameRules(
  id = "knottynines",
  title = "Knotty Nines",
  description = "A more difficult variation of ^trustytwelve^.",
  victoryCondition = VictoryCondition.NoneInStock,
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions = DeckOptions(
    numDecks = 1,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs),
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace),
    lowRank = Some(Rank.Ace)
  ),
  stock = Some(
    StockRules(
      name = "Stock",
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  waste = None,
  foundations = Nil,
  tableaus = Nil,
  cells = None,
  reserves = None,
  pyramids = Nil
)
// scalastyle:on

