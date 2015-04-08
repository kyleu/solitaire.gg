package utils

import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object RequestMessageSerializers {
  private[this] val malformedRequestReads = Json.reads[MalformedRequest]
  private[this] val pingReads = Json.reads[Ping]
  // case object [GetVersion]
  private[this] val debugInfoReads = Json.reads[DebugInfo]

  private[this] val startGameReads = Json.reads[StartGame]
  private[this] val joinGameReads = Json.reads[JoinGame]
  private[this] val observeGameReads = Json.reads[ObserveGame]

  // case object [GetPossibleMoves]
  private[this] val selectCardReads = Json.reads[SelectCard]
  private[this] val selectPileReads = Json.reads[SelectPile]
  private[this] val moveCardsReads = Json.reads[MoveCards]

  // case object Undo
  // case object Redo

  implicit val requestMessageReads: Reads[RequestMessage] = (
    (__ \ 'c).read[String] and
    (__ \ 'v).read[JsValue]
  ) { (c, v) =>
      val jsResult = c match {
        case "MalformedRequest" => malformedRequestReads.reads(v)
        case "Ping" => pingReads.reads(v)
        case "GetVersion" => JsSuccess(GetVersion)
        case "DebugInfo" => debugInfoReads.reads(v)

        case "StartGame" => startGameReads.reads(v)
        case "JoinGame" => joinGameReads.reads(v)
        case "ObserveGame" => observeGameReads.reads(v)

        case "GetPossibleMoves" => JsSuccess(GetPossibleMoves)
        case "SelectCard" => selectCardReads.reads(v)
        case "SelectPile" => selectPileReads.reads(v)
        case "MoveCards" => moveCardsReads.reads(v)

        case "Undo" => JsSuccess(Undo)
        case "Redo" => JsSuccess(Redo)

        case _ => JsSuccess(MalformedRequest("UnknownType", "c: " + c + ", v: " + Json.stringify(v)))
      }
      jsResult match {
        case rm: JsSuccess[RequestMessage @unchecked] => rm.get
        case e: JsError => throw new IllegalArgumentException("Error parsing json for [" + c + "]: " + JsError.toFlatJson(e).toString())
      }
    }
}
