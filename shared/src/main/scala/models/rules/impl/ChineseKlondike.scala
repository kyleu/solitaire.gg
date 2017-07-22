package models.rules.impl

import models.card.Suit
import models.rules._

object ChineseKlondike extends GameRules(
  id = "chineseklondike",
  completed = true,
  title = "Chinese Klondike",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_klondike.htm")),
  layout = "swf|.:::t",
  deckOptions = DeckOptions(numDecks = 4, suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 16, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
