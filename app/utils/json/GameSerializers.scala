package utils.json

import models.card.{Card, Deck, Rank, Suit}
import models.game.{GamePlayer, GameState}
import models.pile.Pile
import models.pile.options.{ClientPileOptions, PileOptions}
import models.pile.set.PileSet
import models.settings._
import play.api.libs.json._

object GameSerializers {
  implicit val rankReads = new Reads[Rank] {
    override def reads(json: JsValue): JsResult[Rank] = json match {
      case JsString(s) => if (s.length == 1) {
        JsSuccess(Rank.withValue(s.head))
      } else {
        JsError(s"Invalid card suit value [$s].")
      }
      case _ => JsError(s"Invalid card suit value [${Json.stringify(json)}].")
    }
  }
  implicit val rankWrites = new Writes[Rank] {
    override def writes(r: Rank) = JsString(r.value.toString)
  }

  implicit val suitReads = new Reads[Suit] {
    override def reads(json: JsValue): JsResult[Suit] = json match {
      case JsString(s) => if (s.length == 1) {
        JsSuccess(Suit.withValue(s.head))
      } else {
        JsError(s"Invalid card suit value [$s].")
      }
      case _ => JsError(s"Invalid card suit value [${Json.stringify(json)}].")
    }
  }
  implicit val suitWrites = new Writes[Suit] {
    override def writes(s: Suit) = JsString(s.value.toString)
  }

  implicit val cardReads = Json.reads[Card]
  implicit val cardWrites = Json.writes[Card]

  implicit val deckWrites = Json.writes[Deck]

  private[this] val clientPileOptionsWrites = Json.writes[ClientPileOptions]
  implicit val pileOptionsWrites = new Writes[PileOptions] {
    override def writes(po: PileOptions) = clientPileOptionsWrites.writes(ClientPileOptions.fromPileOptions(po))
  }
  implicit val pileWrites = Json.writes[Pile]
  implicit val pileSetWrites = Json.writes[PileSet]

  implicit val cardSetWrites = new Writes[CardSet] {
    override def writes(cs: CardSet) = JsString(cs.getClass.getSimpleName)
  }
  implicit val cardBackWrites = new Writes[CardBack] {
    override def writes(x: CardBack) = JsString(x.value)
  }
  implicit val cardBackgroundWrites = new Writes[CardBlank] {
    override def writes(x: CardBlank) = JsString(x.value)
  }
  implicit val cardFacesWrites = new Writes[CardFaces] {
    override def writes(x: CardFaces) = JsString(x.value)
  }
  implicit val cardLayoutWrites = new Writes[CardLayout] {
    override def writes(x: CardLayout) = JsString(x.value)
  }
  implicit val cardRanksWrites = new Writes[CardRanks] {
    override def writes(x: CardRanks) = JsString(x.value)
  }
  implicit val cardSuitsWrites = new Writes[CardSuits] {
    override def writes(x: CardSuits) = JsString(x.value)
  }
  implicit val emptyPileWrites = new Writes[EmptyPile] {
    override def writes(x: EmptyPile) = JsString(x.value)
  }
  implicit val menuPositionWrites = new Writes[MenuPosition] {
    override def writes(x: MenuPosition) = JsString(x.value)
  }

  implicit val settingsWrites = Json.writes[Settings]
  implicit val gamePlayerWrites = Json.writes[GamePlayer]
  implicit val gameStateWrites = Json.writes[GameState]
}
