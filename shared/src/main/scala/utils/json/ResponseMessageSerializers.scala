package utils.json

import models._
import upickle._
import upickle.legacy._

import BaseSerializers._
import GameSerializers._

object ResponseMessageSerializers {
  private implicit val responseMessageWriter: Writer[ResponseMessage] = Writer[ResponseMessage] {
    case rm =>
      val jsVal = rm match {
        case vr: VersionResponse => writeJs(vr)
        case p: Pong => writeJs(p)
        case gj: GameJoined => writeJs(gj)
        case cr: CardRevealed => writeJs(cr)
        case ch: CardHidden => writeJs(ch)
        case cmc: CardMoveCancelled => writeJs(cmc)
        case cm: CardMoved => writeJs(cm)
        case cm: CardsMoved => writeJs(cm)
        case pm: PossibleMoves => writeJs(pm)
        case gw: GameWon => writeJs(gw)
        case gw: GameLost => writeJs(gw)
        case ms: MessageSet => writeJs(ms)
        case _ => throw new IllegalStateException(s"Invalid Message [${rm.getClass.getName}].")
      }
      val jsArray = jsVal match { case arr: Js.Arr => arr; case _ => throw new IllegalArgumentException(jsVal.toString) }
      jsArray.value.toList match {
        case one :: two :: Nil =>
          val oneStr = Js.Str(one match {
            case s: Js.Str => s.value.replace("models.", "")
            case _ => throw new IllegalStateException()
          })
          Js.Obj("c" -> oneStr, "v" -> two)
        case _ => throw new IllegalStateException()
      }
  }

  def write(rm: ResponseMessage) = writeJs(rm)
}
