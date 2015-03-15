import java.util.UUID

import models.game.{Rank, Suit, Card}
import org.specs2._

class CardSpec extends mutable.Specification {
  val aceHearts = Card(UUID.randomUUID, Rank.Ace, Suit.Hearts, u = true)
  val tenHearts = Card(UUID.randomUUID, Rank.Ten, Suit.Hearts, u = true)
  val aceSpades = Card(UUID.randomUUID, Rank.Ace, Suit.Spades, u = true)

  "Cards" should {
    "Serialize correctly." in {
      aceHearts.toString must === ("AH+: " + aceHearts.id.toString.substring(0, 8))
    }
    "Compare to others correctly." in {
      aceSpades.compareTo(aceSpades) must === (0)
      aceHearts.compareTo(aceSpades) must === (13)
      aceSpades.compareTo(aceHearts) must === (-13)
      aceSpades.compareTo(tenHearts) must === (-9)
      aceHearts.compareTo(tenHearts) must === (4)
    }
    "Compare to others with Ace low." in {
      aceSpades.compareAceLow(aceSpades) must === (0)
      aceHearts.compareAceLow(aceSpades) must === (13)
      aceSpades.compareAceLow(aceHearts) must === (-13)
      aceSpades.compareAceLow(tenHearts) must === (-22)
      aceHearts.compareAceLow(tenHearts) must === (-9)
    }
  }
}
