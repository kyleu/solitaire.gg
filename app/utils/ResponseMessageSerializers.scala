package utils

import models._
import utils.GameSerializers._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object ResponseMessageSerializers {
  private[this] val serverErrorWrites = Json.writes[ServerError]
  private[this] val pongWrites = Json.writes[Pong]
  private[this] val versionResponseWrites = Json.writes[VersionResponse]
  private[this] val disconnectedResponseWrites = Json.writes[Disconnected]

  private[this] implicit val possibleMoveWrites = Json.writes[PossibleMove]
  private[this] implicit val possibleMovesWrites = Json.writes[PossibleMoves]

  private[this] val gameJoinedWrites = Json.writes[GameJoined]
  private[this] val gameLostWrites = Json.writes[GameLost]
  private[this] val gameWonWrites = Json.writes[GameWon]

  private[this] val cardRevealedWrites = Json.writes[CardRevealed]
  private[this] val cardHiddenWrites = Json.writes[CardHidden]

  private[this] val cardMoveCancelledWrites = Json.writes[CardMoveCancelled]
  private[this] val cardMovedWrites = Json.writes[CardMoved]
  private[this] val cardsMovedWrites = Json.writes[CardsMoved]

  implicit val responseMessageWrites = Writes[ResponseMessage] { r: ResponseMessage =>
    val json = r match {
      case se: ServerError => serverErrorWrites.writes(se)
      case p: Pong => pongWrites.writes(p)
      case vr: VersionResponse => versionResponseWrites.writes(vr)
      case SendDebugInfo => JsObject(Nil)
      case d: Disconnected => disconnectedResponseWrites.writes(d)

      case pm: PossibleMoves => possibleMovesWrites.writes(pm)

      case gj: GameJoined => gameJoinedWrites.writes(gj)
      case gl: GameLost => gameLostWrites.writes(gl)
      case gw: GameWon => gameWonWrites.writes(gw)

      case cr: CardRevealed => cardRevealedWrites.writes(cr)
      case cr: CardHidden => cardHiddenWrites.writes(cr)

      case cmc: CardMoveCancelled => cardMoveCancelledWrites.writes(cmc)
      case cm: CardMoved => cardMovedWrites.writes(cm)
      case cm: CardsMoved => cardsMovedWrites.writes(cm)

      case _ => throw new IllegalArgumentException("Unhandled ResponseMessage type [" + r.getClass.getSimpleName + "].")
    }
    JsObject(Seq("c" -> JsString(r.getClass.getSimpleName.replace("$", "")), "v" -> json))
  }

  val messageSetWrites = Writes[MessageSet] { ms: MessageSet =>
    JsObject(Seq("c" -> JsString("MessageSet"), "v" -> JsObject(Seq("messages" -> JsArray(ms.messages.map(responseMessageWrites.writes))))))
  }
}
