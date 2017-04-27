package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import msg.SocketMessage
import models.user.User
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

  def solitaire = req("solitaire") { implicit request =>
    Future.successful(Ok(views.html.solitaire.solitaire(app.config.debug)))
  }

  def connect() = WebSocket.acceptOrResult[SocketMessage, SocketMessage] { implicit request =>
    def messages(key: String, args: Seq[Any]) = messagesApi.apply(key, args: _*)
    Future.successful(Right(ActorFlow.actorRef { out =>
      val user = User.placeholder
      SocketService.props(None, app.supervisor, user, out, request.remoteAddress, messages)
    }))
  }
}
