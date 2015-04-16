import java.util.UUID

import models._
import models.game.pile.{ ClientPileOptions, Pile, PileOptions }
import models.game.{ Card, GameState, Rank, Suit }
import upickle._

import scala.scalajs.js

object JsonUtils {
  private implicit val rankWriter = upickle.Writer[Rank]{ case r => Js.Str(r.toChar.toString) }
  private implicit val suitWriter = upickle.Writer[Suit]{ case s => Js.Str(s.toChar.toString) }
  private implicit val cardWriter = upickle.Writer[Card]{ case c => writeJs(c) }
  private implicit val clientPileOptionsWriter = upickle.Writer[ClientPileOptions]{ case cpo => Js.Obj(
    "cardsShown" -> cpo.cardsShown.map(x => Js.Num(x)).getOrElse(Js.Null: Js.Value),
    "direction" -> cpo.direction.map(x => Js.Str(x)).getOrElse(Js.Null: Js.Value),
    "dragFromConstraint" -> Js.Str(cpo.dragFromConstraint),
    "dragFromOptions" -> Js.Obj(Seq.empty[(String, Js.Value)]: _*)
  ) }
  private implicit val pileOptionsWriter = upickle.Writer[PileOptions] { case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  private implicit val pileWriter = upickle.Writer[Pile] { case p => writeJs(p) }
  private implicit val gameStateWriter = upickle.Writer[GameState] { case gs => writeJs(gs) }
  private implicit val gameJoinedWriter = upickle.Writer[GameJoined] { case gj => writeJs(gj) }
  private implicit val possibleMoveWriter = upickle.Writer[PossibleMove] { case pm => Js.Obj(
    "moveType" -> writeJs(pm.moveType),
    "cards" -> writeJs(pm.cards),
    "sourcePile" -> writeJs(pm.sourcePile),
    "targetPile" -> pm.targetPile.map(x => Js.Str(x): Js.Value).getOrElse(Js.Null: Js.Value)
  ) }
  private implicit val possibleMovesWriter = upickle.Writer[PossibleMoves] { case pm => writeJs(pm) }

  private implicit val responseMessageWriter: Writer[ResponseMessage] = upickle.Writer[ResponseMessage] {
    case rm =>
      val jsVal = rm match {
        case vr: VersionResponse => writeJs(vr)
        case p: Pong => writeJs(p)
        case gj: GameJoined => writeJs(gj)
        case cr: CardRevealed => writeJs(cr)
        case cmc: CardMoveCancelled => writeJs(cmc)
        case cm: CardMoved => writeJs(cm)
        case cm: CardsMoved => writeJs(cm)
        case pm: PossibleMoves => writeJs(pm)
        case gw: GameWon => writeJs(gw)
        case ms: MessageSet => writeJs(ms)
        case _ => throw new IllegalStateException(rm.getClass.getName)
      }
      val jsArray = jsVal.asInstanceOf[Js.Arr]
      Js.Obj("c" -> Js.Str(jsArray.value.head.asInstanceOf[Js.Str].value.replace("models.", "")), "v" -> jsArray.value.tail.head)
  }

  def write(rm: ResponseMessage) = {
    responseMessageWriter.write(rm)
  }

  def write(j: Js.Value) = {
    upickle.json.write(j)
  }

  def getStringOption(o: js.Dynamic) = if(o.isInstanceOf[Unit]) { None } else { Some(o.toString) }
  def getInt(o: js.Dynamic) = o.toString.toInt
  def getIntOption(o: js.Dynamic) = if(o.isInstanceOf[Unit]) { None } else { Some(getInt(o)) }
  def getLong(o: js.Dynamic) = o.toString.toLong
  def getUuidSeq(o: js.Dynamic) = {
    println(o)
    Seq(UUID.fromString(o.toString))
  }
}
