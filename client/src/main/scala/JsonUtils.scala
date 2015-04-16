import models.GameJoined
import models.game.{ Suit, Rank, Card, GameState }
import models.game.pile.{ Pile, ClientPileOptions, PileOptions }
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
  private implicit val pileOptionsWriter = upickle.Writer[PileOptions]{ case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  private implicit val pileWriter = upickle.Writer[Pile]{ case p => writeJs(p) }
  private implicit val gameStateWriter = upickle.Writer[GameState]{ case gs => writeJs(gs) }
  private implicit val gameJoinedWriter = upickle.Writer[GameJoined]{ case gj => writeJs(gj) }

  def writeGameJoined(gj: GameJoined) = write(gj)

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
