package services.test

import models.card.{Card, Rank, Suit}
import models.test.Test

class CardTests {
  val aceHearts = Card(0, Rank.Ace, Suit.Hearts, u = true)
  val tenHearts = Card(1, Rank.Ten, Suit.Hearts)
  val aceSpades = Card(2, Rank.Ace, Suit.Spades, u = true)

  val all = Test("card", () => {
    assert(aceHearts.toString == "0:AH+")
    assert(tenHearts.toString == "1:XH-")
    assert(aceSpades.toString == "2:AS+")
  }).toTree
}
