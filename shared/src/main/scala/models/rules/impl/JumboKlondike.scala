package models.rules.impl

import models.card.Suit
import models.rules._

object JumboKlondike extends GameRules(
  id = "jumboklondike",
  completed = false,
  title = "Jumbo Klondike",
  like = Some("klondike"),
  layout = "swf|t",
  deckOptions = DeckOptions(suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs, Suit.Stars, Suit.Tridents)),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
