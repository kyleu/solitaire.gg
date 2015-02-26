package utils

import models._
import utils.GameSerializers._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object ResponseMessageSerializers {
  private val serverErrorWrites = Json.writes[ServerError]
  private val pongWrites = Json.writes[Pong]
  private val versionResponseWrites = Json.writes[VersionResponse]

  private val gameJoinedWrites = Json.writes[GameJoined]

  private val revealCardWrites = Json.writes[RevealCard]
  private val cardMovedWrites = Json.writes[CardMoved]
  private val cancelCardMoveWrites = Json.writes[CancelCardMove]

  implicit val responseMessageWrites = Writes[ResponseMessage] { r: ResponseMessage =>
    val json = r match {
      case se: ServerError => serverErrorWrites.writes(se)
      case p: Pong => pongWrites.writes(p)
      case vr: VersionResponse => versionResponseWrites.writes(vr)

      case gj: GameJoined => gameJoinedWrites.writes(gj)
      case rc: RevealCard => revealCardWrites.writes(rc)
      case cm: CardMoved => cardMovedWrites.writes(cm)
      case ccm: CancelCardMove => cancelCardMoveWrites.writes(ccm)
      case _ => throw new IllegalArgumentException("Unhandled ResponseMessage type [" + r.getClass.getSimpleName + "].")
    }
    JsObject(Seq("c" -> JsString(r.getClass.getSimpleName), "v" -> json))
  }

  val messageSetWrites = Writes[MessageSet] { ms: MessageSet =>
    JsObject(Seq("c" -> JsString("MessageSet"), "v" -> JsObject(Seq("messages" -> JsArray(ms.messages.map(responseMessageWrites.writes))))))
  }
}
