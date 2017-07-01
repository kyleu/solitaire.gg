package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import msg.req.SocketRequestMessage
import msg.rsp.SocketResponseMessage
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket
import services.socket.SocketService
import utils.Application
import utils.FutureUtils.defaultContext
import utils.web.MessageFrameFormatter

@javax.inject.Singleton
class SolitaireController @javax.inject.Inject() (
    override val app: Application,
    implicit val system: ActorSystem,
    implicit val materializer: Materializer
) extends BaseController {
  private[this] implicit val t = new MessageFrameFormatter(app.config.debug).transformer

  def start() = startArgs("")

  def startArgs(path: String) = req("solitaire") { implicit request =>
    getUser(request).map { u =>
      Ok(views.html.solitaire.solitaire(u.settings, app.config.debug)).withSession(request.session + ("userId" -> u.id.toString))
    }
  }

  def connect() = WebSocket.acceptOrResult[SocketRequestMessage, SocketResponseMessage] { implicit request =>
    def messages(key: String, args: Seq[Any]) = messagesApi.apply(key, args: _*)(request.lang)
    getUser(request).map { user =>
      Right(ActorFlow.actorRef { out =>
        SocketService.props(None, app.supervisor, user, out, request.remoteAddress, messages)
      })
    }
  }
}
