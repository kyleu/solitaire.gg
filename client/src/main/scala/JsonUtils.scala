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
    "direction" -> cpo.direction.map(x => Js.Str(x.toString)).getOrElse(Js.Null: Js.Value),
    "dragFromConstraint" -> Js.Str(cpo.dragFromConstraint),
    "dragFromOptions" -> Js.Obj(Seq.empty[(String, Js.Value)]: _*)
  ) }
  private implicit val pileOptionsWriter = upickle.Writer[PileOptions] { case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  private implicit val pileWriter = upickle.Writer[Pile] { case p => writeJs(p) }
  private implicit val gameStateWriter = upickle.Writer[GameState] { case gs => writeJs(gs) }
  private implicit val gameJoinedWriter = upickle.Writer[GameJoined] { case gj => writeJs(gj) }

  private implicit val responseMessageWriter: Writer[ResponseMessage] = upickle.Writer[ResponseMessage] {
    case rm => rm match {
      case vr: VersionResponse => writeJs(vr)
      case p: Pong => writeJs(p)
      case gj: GameJoined => writeJs(gj)
      case cr: CardRevealed => writeJs(cr)
      case cmc: CardMoveCancelled => writeJs(cmc)
      case cm: CardMoved => writeJs(cm)
      case pm: PossibleMoves => writeJs(pm)
      case gw: GameWon => writeJs(gw)
      case ms: MessageSet => writeJs(ms)
      case _ => throw new IllegalStateException(rm.getClass.getName)
    }
  }

  def write(rm: ResponseMessage) = {
    responseMessageWriter.write(rm)
  }

  def w(j: Js.Value) = {
    upickle.json.write(j)
  }

  def getInt(o: js.Dynamic) = o.asInstanceOf[Double].toInt

  def getIntOption(o: js.Dynamic) = {
    if(o.isInstanceOf[Unit]) {
      None
    } else {
      Some(getInt(o))
    }
  }

  def getLong(o: js.Dynamic) = o.asInstanceOf[Double].toLong
}
