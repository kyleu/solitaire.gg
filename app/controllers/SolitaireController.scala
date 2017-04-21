package controllers

import java.util.UUID

import akka.actor.ActorSystem
import akka.stream.Materializer
import models.user.{User, UserPreferences}
import models.{RequestMessage, ResponseMessage}
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket
import services.socket.SocketService
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

  def play = req("solitaire") { implicit request =>
    Future.successful(Ok(views.html.solitaire(app.config.debug)))
  }

  def connect() = WebSocket.accept[RequestMessage, ResponseMessage] { request =>
    val user = User(UUID.randomUUID, None, None, UserPreferences())
    ActorFlow.actorRef(out => SocketService.props(None, app.supervisor, user, out, request.remoteAddress))
  }
}
