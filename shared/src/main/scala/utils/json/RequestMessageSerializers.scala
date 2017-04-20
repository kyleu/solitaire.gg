package utils.json

import models._
import upickle._
import upickle.legacy._

import BaseSerializers._

object RequestMessageSerializers {
  private implicit val requestMessageReader: Reader[RequestMessage] = Reader[RequestMessage] {
    case json: Js.Obj =>
      val c = json.value.find(_._1 == "c").getOrElse(throw new IllegalStateException())._2 match {
        case Js.Str(s) => s
        case _ => throw new IllegalStateException()
      }
      val v = json.value.find(_._1 == "v").getOrElse(throw new IllegalStateException())._2 match {
        case o: Js.Obj => o
        case _ => throw new IllegalStateException()
      }
      val ret: RequestMessage = c match {
        case "MalformedRequest" => readJs[MalformedRequest](v)
        case "Ping" => readJs[Ping](v)
        case "GetVersion" => GetVersion
        case "DebugInfo" => readJs[DebugInfo](v)

        case "StartGame" => readJs[StartGame](v)
        case "JoinGame" => readJs[JoinGame](v)
        case "ObserveGame" => readJs[ObserveGame](v)

        case "GetPossibleMoves" => GetPossibleMoves

        case "SelectCard" => readJs[SelectCard](v)
        case "SelectPile" => readJs[SelectPile](v)
        case "MoveCards" => readJs[MoveCards](v)

        case "Undo" => Undo
        case "Redo" => Redo

        case "SetPreference" => readJs[SetPreference](v)

        case _ => MalformedRequest("UnknownType", s"c: $c, v: ${v.toString}")
      }
      ret
  }

  def read(json: Js.Value) = readJs[RequestMessage](json)
}
