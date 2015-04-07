package utils

import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object RequestMessageSerializers {
  private val malformedRequestReads = Json.reads[MalformedRequest]
  private val pingReads = Json.reads[Ping]
  // case object [GetVersion]
  private val debugInfoReads = Json.reads[DebugInfo]

  private val startGameReads = Json.reads[StartGame]
  private val joinGameReads = Json.reads[JoinGame]
  private val observeGameReads = Json.reads[ObserveGame]

  // case object [GetPossibleMoves]
  private val selectCardReads = Json.reads[SelectCard]
  private val selectPileReads = Json.reads[SelectPile]
  private val moveCardsReads = Json.reads[MoveCards]

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
