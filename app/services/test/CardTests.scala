package services.test

import java.util.UUID

import models.card.{ Card, Rank, Suit }
import models.test.Test

class CardTests {
  val aceHearts = Card(UUID.randomUUID, Rank.Ace, Suit.Hearts, u = true)
  val tenHearts = Card(UUID.randomUUID, Rank.Ten, Suit.Hearts, u = false)
  val aceSpades = Card(UUID.randomUUID, Rank.Ace, Suit.Spades, u = true)

  val all = Test("card", () => {
    assert(aceHearts.toString == "AH+: " + aceHearts.id.toString.substring(0, 8))
    assert(tenHearts.toString == "XH-: " + tenHearts.id.toString.substring(0, 8))
    assert(aceSpades.toString == "AS+: " + aceSpades.id.toString.substring(0, 8))
  }).toTree
}
