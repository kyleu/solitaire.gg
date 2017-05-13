package controllers

import java.util.UUID

import akka.actor.ActorSystem
import akka.stream.Materializer
import models.settings.BackgroundPattern
import msg.SocketMessage
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.streams.ActorFlow
import play.api.mvc.{RequestHeader, WebSocket}
import services.socket.SocketService
import services.user.UserService
import utils.Application
import utils.web.MessageFrameFormatter

import scala.concurrent.Future

@javax.inject.Singleton
class SolitaireController @javax.inject.Inject() (
    override val app: Application,
    implicit val system: ActorSystem,
    implicit val materializer: Materializer
) extends BaseController {
  private[this] implicit val t = new MessageFrameFormatter(app.config.debug).transformer

  private[this] def getUser(request: RequestHeader) = request.session.get("userId") match {
    case Some(id) if id.length == 36 => UserService.getById(UUID.fromString(id)).flatMap {
      case Some(user) => Future.successful(user)
      case None => UserService.newUser()
    }
    case Some(id) => UserService.newUser()
    case None => UserService.newUser()
  }

  def start() = startArgs("")

  def startArgs(path: String) = req("solitaire") { implicit request =>
    getUser(request).map { u =>
      Ok(views.html.solitaire.solitaire(u.settings, app.config.debug)).withSession(request.session + ("userId" -> u.id.toString))
    }
  }

  def connect() = WebSocket.acceptOrResult[SocketMessage, SocketMessage] { implicit request =>
    def messages(key: String, args: Seq[Any]) = messagesApi.apply(key, args: _*)
    getUser(request).map { user =>
      Right(ActorFlow.actorRef { out =>
        SocketService.props(None, app.supervisor, user, out, request.remoteAddress, messages)
      })
    }
  }
}
