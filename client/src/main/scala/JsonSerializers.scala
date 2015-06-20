import models._
import models.game.pile.Pile
import models.game.pile.options.{ PileOptions, ClientPileOptions }
import models.game.{ Card, GameState, Rank, Suit }
import upickle._

object JsonSerializers {
  private implicit val stringOptionWriter = upickle.Writer[Option[String]] {
    case Some(s) => Js.Str(s)
    case None => Js.Null
  }
  private implicit val intOptionWriter = upickle.Writer[Option[Int]] {
    case Some(i) => Js.Num(i)
    case None => Js.Null
  }
  private implicit val boolOptionWriter = upickle.Writer[Option[Boolean]] {
    case Some(b) => if (b) { Js.True } else { Js.False }
    case None => Js.Null
  }

  private implicit val rankWriter = upickle.Writer[Rank] { case r => Js.Str(r.toChar.toString) }
  private implicit val suitWriter = upickle.Writer[Suit] { case s => Js.Str(s.toChar.toString) }
  private implicit val cardWriter = upickle.Writer[Card] { case c => writeJs(c) }

  private implicit val clientPileOptionsWriter = upickle.Writer[ClientPileOptions] {
    case cpo =>
      val dfo = cpo.dragFromOptions.map(opts => Js.Obj(opts.map(opt => opt._1 -> Js.Str(opt._2)).toSeq: _*)).getOrElse(Js.Null)
      Js.Obj(
        "cardsShown" -> writeJs(cpo.cardsShown),
        "direction" -> writeJs(cpo.direction),
        "dragFromConstraint" -> Js.Str(cpo.dragFromConstraint),
        "dragFromOptions" -> dfo
      )
  }
  private implicit val pileOptionsWriter = upickle.Writer[PileOptions] { case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  private implicit val pileWriter = upickle.Writer[Pile] { case p => writeJs(p) }

  private implicit val possibleMoveWriter = upickle.Writer[PossibleMove] { case pm => writeJs(pm) }
  private implicit val possibleMovesWriter = upickle.Writer[PossibleMoves] { case pm => writeJs(pm) }

  private implicit val cardMovedWriter = upickle.Writer[CardMoved] { case cm => writeJs(cm) }
  private implicit val cardsMovedWriter = upickle.Writer[CardsMoved] { case cm => writeJs(cm) }

  private implicit val gameStateWriter = upickle.Writer[GameState] { case gs => writeJs(gs) }
  private implicit val gameJoinedWriter = upickle.Writer[GameJoined] { case gj => writeJs(gj) }

  private implicit val responseMessageWriter: Writer[ResponseMessage] = upickle.Writer[ResponseMessage] {
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
