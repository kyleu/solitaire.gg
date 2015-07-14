package utils.json

import models.card.{ Card, Deck, Rank, Suit }
import models.game.{ GamePlayer, GameState }
import models.pile.Pile
import models.pile.options.{ ClientPileOptions, PileOptions }
import models.pile.set.PileSet
import models.user.{ CardPreferences, UserPreferences }
import play.api.libs.json._

object GameSerializers {
  implicit val rankReads = new Reads[Rank] {
    override def reads(json: JsValue): JsResult[Rank] = json match {
      case JsString(s) => if (s.length == 1) {
        JsSuccess(Rank.allByChar(s.head))
      } else {
        JsError(s"Invalid card suit value [$s].")
      }
      case _ => JsError(s"Invalid card suit value [${Json.stringify(json)}].")
    }
  }
  implicit val rankWrites = new Writes[Rank] {
    override def writes(r: Rank) = JsString(r.toChar.toString)
  }

  implicit val suitReads = new Reads[Suit] {
    override def reads(json: JsValue): JsResult[Suit] = json match {
      case JsString(s) => if (s.length == 1) {
        JsSuccess(Suit.fromChar(s.head))
      } else {
        JsError(s"Invalid card suit value [$s].")
      }
      case _ => JsError(s"Invalid card suit value [${Json.stringify(json)}].")
    }
  }
  implicit val suitWrites = new Writes[Suit] {
    override def writes(s: Suit) = JsString(s.toChar.toString)
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

  implicit val cardPreferencesWrites = Json.writes[CardPreferences]
  implicit val userPreferencesWrites = Json.writes[UserPreferences]
  implicit val gamePlayerWrites = Json.writes[GamePlayer]
  implicit val gameStateWrites = Json.writes[GameState]
}
