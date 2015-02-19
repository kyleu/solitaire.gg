package utils

import models.game._
import play.api.libs.json._

object GameSerializers {
  implicit val rankReads = new Reads[Rank] {
    override def reads(json: JsValue): JsResult[Rank] = json match {
      case JsString(s) => if(s.length == 1) {
        JsSuccess(Rank.fromChar(s.head))
      } else {
        JsError("Invalid card suit value [" + s + "].")
      }
      case _ => JsError("Invalid card suit value [" + Json.stringify(json) + "].")
    }
  }
  implicit val rankWrites = new Writes[Rank] {
    override def writes(r: Rank) = JsString(r.toChar.toString)
  }

  implicit val suitReads = new Reads[Suit] {
    override def reads(json: JsValue): JsResult[Suit] = json match {
      case JsString(s) => if(s.length == 1) {
        JsSuccess(Suit.fromChar(s.head))
      } else {
        JsError("Invalid card suit value [" + s + "].")
      }
      case _ => JsError("Invalid card suit value [" + Json.stringify(json) + "].")
    }
  }
  implicit val suitWrites = new Writes[Suit] {
    override def writes(s: Suit) = JsString(s.toChar.toString)
  }

  implicit val cardReads = Json.reads[Card]
  implicit val cardWrites = Json.writes[Card]

  implicit val deckReads = Json.reads[Deck]
  implicit val deckWrites = Json.writes[Deck]

  implicit val pileReads = Json.reads[Pile]
  implicit val pileWrites = Json.writes[Pile]

  implicit val pileLocationReads = Json.reads[PileLocation]
  implicit val pileLocationWrites = Json.writes[PileLocation]

  implicit val layoutReads = Json.reads[Layout]
  implicit val layoutWrites = Json.writes[Layout]

  implicit val gameStateReads = Json.reads[GameState]
  implicit val gameStateWrites = Json.writes[GameState]
}
