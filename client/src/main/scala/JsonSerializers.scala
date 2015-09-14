import models._
import models.card.{ Card, Suit, Rank }
import models.game.GameState
import models.pile.Pile
import models.pile.options.{ PileOptions, ClientPileOptions }
import upickle._
import upickle.legacy._

object JsonSerializers {
  private implicit val stringOptionWriter = Writer[Option[String]] {
    case Some(s) => Js.Str(s)
    case None => Js.Null
  }
  private implicit val intOptionWriter = Writer[Option[Int]] {
    case Some(i) => Js.Num(i.toDouble)
    case None => Js.Null
  }
  private implicit val boolOptionWriter = Writer[Option[Boolean]] {
    case Some(b) => if (b) { Js.True } else { Js.False }
    case None => Js.Null
  }

  private implicit val rankWriter = Writer[Rank] { case r => Js.Str(r.toChar.toString) }
  private implicit val suitWriter = Writer[Suit] { case s => Js.Str(s.toChar.toString) }
  private implicit val cardWriter = Writer[Card] { case c => writeJs(c) }

  private implicit val clientPileOptionsWriter = Writer[ClientPileOptions] {
    case cpo =>
      val dfo = cpo.dragFromOptions.map(opts => Js.Obj(opts.map(opt => opt._1 -> Js.Str(opt._2)).toSeq: _*)).getOrElse(Js.Null)
      Js.Obj(
        "cardsShown" -> writeJs(cpo.cardsShown),
        "direction" -> writeJs(cpo.direction),
        "dragFromConstraint" -> Js.Str(cpo.dragFromConstraint),
        "dragFromOptions" -> dfo
      )
  }
  private implicit val pileOptionsWriter = Writer[PileOptions] { case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  private implicit val pileWriter = Writer[Pile] { case p => writeJs(p) }

  private implicit val possibleMoveWriter = Writer[PossibleMove] { case pm => writeJs(pm) }
  private implicit val possibleMovesWriter = Writer[PossibleMoves] { case pm => writeJs(pm) }

  private implicit val cardMovedWriter = Writer[CardMoved] { case cm => writeJs(cm) }
  private implicit val cardsMovedWriter = Writer[CardsMoved] { case cm => writeJs(cm) }

  private implicit val gameStateWriter = Writer[GameState] { case gs => writeJs(gs) }
  private implicit val gameJoinedWriter = Writer[GameJoined] { case gj => writeJs(gj) }

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

  def write(rm: ResponseMessage) = responseMessageWriter.write(rm)
  def write(j: Js.Value) = json.write(j)
}
