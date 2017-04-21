package utils

import models.card.{Rank, Suit}
import models.pile.options.{ClientPileOptions, PileOptions}
import upickle.Js
import upickle.default._

object JsonSerializers {
  implicit val rankReader = Reader[Rank] { case Js.Str(x) => Rank.allByChar(x.head) }
  implicit val rankWriter = Writer[Rank] { case r => Js.Str(r.toChar.toString) }
  implicit val suitReader = Reader[Suit] { case Js.Str(x) => Suit.fromChar(x.head) }
  implicit val suitWriter = Writer[Suit] { case s => Js.Str(s.toChar.toString) }

  implicit val pileOptionsWriter = Writer[PileOptions] {
    case cpo => Js.Obj()
  }
}
