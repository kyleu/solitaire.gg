package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object SpiderOneSuit extends GameRules(
  id = "spideronesuit",
  title = "Spider One Suit",
  description = "^spider^ with nothing but spades, spades, spades, as far as the eye can see.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions = DeckOptions(
    numDecks = 8,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs),
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace),
    lowRank = Some(Rank.Ace)
  ),
  stock = Some(
    Stock(
      name = "Stock",
      dealTo = StockDealTo.TableauIfNoneEmpty,
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

