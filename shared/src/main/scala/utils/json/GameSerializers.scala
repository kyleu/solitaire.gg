package utils.json

import models._
import models.card.{Card, Rank, Suit}
import models.game.GameState
import models.pile.Pile
import models.pile.options.{ClientPileOptions, PileOptions}
import upickle._
import upickle.legacy._

object GameSerializers {
  // Card
  implicit val rankReader = Reader[Rank] { case Js.Str(x) => Rank.allByChar(x.head) }
  implicit val rankWriter = Writer[Rank] { case r => Js.Str(r.toChar.toString) }
  implicit val suitReader = Reader[Suit] { case Js.Str(x) => Suit.fromChar(x.head) }
  implicit val suitWriter = Writer[Suit] { case s => Js.Str(s.toChar.toString) }

  // GameState
  implicit val clientPileOptionsWriter = Writer[ClientPileOptions] {
    case cpo =>
      val dfo = cpo.dragFromOptions.map(opts => Js.Obj(opts.map(opt => opt._1 -> Js.Str(opt._2)).toSeq: _*)).getOrElse(Js.Null)
      Js.Obj(
        "cardsShown" -> writeJs(cpo.cardsShown),
        "direction" -> writeJs(cpo.direction),
        "dragFromConstraint" -> Js.Str(cpo.dragFromConstraint),
        "dragFromOptions" -> dfo
      )
  }

  implicit val pileOptionsReader = Reader[PileOptions] { case po => PileOptions() }
  implicit val pileOptionsWriter = Writer[PileOptions] { case po => writeJs(ClientPileOptions.fromPileOptions(po)) }
  implicit val pileReader = Reader[Pile] { case p => readJs[Pile](p) }
  implicit val pileWriter = Writer[Pile] { case p => writeJs(p) }

  implicit val possibleMoveReader = Reader[PossibleMove] { case pm => readJs[PossibleMove](pm) }
  implicit val possibleMoveWriter = Writer[PossibleMove] { case pm => writeJs(pm) }
  implicit val possibleMovesReader = Reader[PossibleMoves] { case pm => readJs[PossibleMoves](pm) }
  implicit val possibleMovesWriter = Writer[PossibleMoves] { case pm => writeJs(pm) }

  implicit val cardMovedWriter = Writer[CardMoved] { case cm => writeJs(cm) }
  implicit val cardsMovedWriter = Writer[CardsMoved] { case cm => writeJs(cm) }

  implicit val gameStateWriter = Writer[GameState] { case gs => writeJs(gs) }
}
