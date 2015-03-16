package utils

import models._
import utils.GameSerializers._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object ResponseMessageSerializers {
  private val serverErrorWrites = Json.writes[ServerError]
  private val pongWrites = Json.writes[Pong]
  private val versionResponseWrites = Json.writes[VersionResponse]

  private implicit val possibleMoveWrites = Json.writes[PossibleMove]
  private implicit val possibleMovesWrites = Json.writes[PossibleMoves]

  private val gameJoinedWrites = Json.writes[GameJoined]
  private val gameLostWrites = Json.writes[GameLost]
  private val gameWonWrites = Json.writes[GameWon]

  private val cardRevealedWrites = Json.writes[CardRevealed]
  private val cardMovedWrites = Json.writes[CardMoved]
  private val cardRemovedWrites = Json.writes[CardRemoved]
  private val cardMoveCancelledWrites = Json.writes[CardMoveCancelled]

  implicit val responseMessageWrites = Writes[ResponseMessage] { r: ResponseMessage =>
    val json = r match {
      case se: ServerError => serverErrorWrites.writes(se)
      case p: Pong => pongWrites.writes(p)
      case vr: VersionResponse => versionResponseWrites.writes(vr)
      case SendDebugInfo => JsObject(Nil)

      case gj: GameJoined => gameJoinedWrites.writes(gj)
      case gl: GameLost => gameLostWrites.writes(gl)
      case gw: GameWon => gameWonWrites.writes(gw)

      case cr: CardRevealed => cardRevealedWrites.writes(cr)
      case cm: CardMoved => cardMovedWrites.writes(cm)
      case cr: CardRemoved => cardRemovedWrites.writes(cr)
      case cmc: CardMoveCancelled => cardMoveCancelledWrites.writes(cmc)

      case _ => throw new IllegalArgumentException("Unhandled ResponseMessage type [" + r.getClass.getSimpleName + "].")
    }
    JsObject(Seq("c" -> JsString(r.getClass.getSimpleName.replace("$", "")), "v" -> json))
  }

  val messageSetWrites = Writes[MessageSet] { ms: MessageSet =>
    JsObject(Seq("c" -> JsString("MessageSet"), "v" -> JsObject(Seq("messages" -> JsArray(ms.messages.map(responseMessageWrites.writes))))))
  }
}
