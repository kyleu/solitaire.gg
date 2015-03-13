package utils

import models.game._
import models.game.pile.constraints.{DragToConstraint, DragFromConstraint, SelectPileConstraint, SelectCardConstraint}
import models.game.pile.{PileOptions, Pile}
import play.api.libs.json._

object GameSerializers {
  implicit val rankReads = new Reads[Rank] {
    override def reads(json: JsValue): JsResult[Rank] = json match {
      case JsString(s) => if(s.length == 1) {
        JsSuccess(Rank.fromChar(s.head))
      } else {
        JsError("Invalid card suit value [" + s + "].")
      }
      case _ => JsError("Invalid card suit value [" + Json.stringify(json) + "].")
    }
  }
  implicit val rankWrites = new Writes[Rank] {
    override def writes(r: Rank) = JsString(r.toChar.toString)
  }

  implicit val suitReads = new Reads[Suit] {
    override def reads(json: JsValue): JsResult[Suit] = json match {
      case JsString(s) => if(s.length == 1) {
        JsSuccess(Suit.fromChar(s.head))
      } else {
        JsError("Invalid card suit value [" + s + "].")
      }
      case _ => JsError("Invalid card suit value [" + Json.stringify(json) + "].")
    }
  }
  implicit val suitWrites = new Writes[Suit] {
    override def writes(s: Suit) = JsString(s.toChar.toString)
  }

  implicit val cardReads = Json.reads[Card]
  implicit val cardWrites = Json.writes[Card]

  implicit val deckWrites = Json.writes[Deck]

  implicit val selectCardConstraintWrites = new Writes[SelectCardConstraint] {
    override def writes(scc: SelectCardConstraint) = JsString(scc.id)
  }
  implicit val selectPileConstraintWrites = new Writes[SelectPileConstraint] {
    override def writes(spc: SelectPileConstraint) = JsString(spc.id)
  }
  implicit val dragFromConstraintWrites = new Writes[DragFromConstraint] {
    override def writes(dfc: DragFromConstraint) = JsString(dfc.id)
  }
  implicit val dragToConstraintWrites = new Writes[DragToConstraint] {
    override def writes(dtc: DragToConstraint) = JsString(dtc.id)
  }

  implicit val pileOptionsWrites = Json.writes[PileOptions]
  implicit val pileWrites = Json.writes[Pile]

  implicit val pileLocationWrites = Json.writes[PileLocation]
  implicit val layoutWrites = Json.writes[Layout]

  implicit val gameStateWrites = Json.writes[GameState]
}
