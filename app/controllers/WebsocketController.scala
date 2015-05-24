package controllers

import akka.actor.ActorRef
import models.{ RequestMessage, ResponseMessage }
import play.api.i18n.Messages
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ AnyContentAsEmpty, Request, WebSocket }
import services.{ ActorSupervisor, ConnectionService }

import scala.concurrent.Future

object WebsocketController extends BaseController {
  import play.api.Play.current
  import utils.MessageFrameFormatter._

  val supervisor = ActorSupervisor.instance

  def connect() = WebSocket.tryAcceptWithActor[RequestMessage, ResponseMessage] { request =>
    implicit val req = Request(request, AnyContentAsEmpty)
    SecuredRequestHandler { securedRequest =>
      Future.successful(HandlerResult(Ok, Some(securedRequest.identity)))
    }.map {
      case HandlerResult(r, Some(user)) =>
        val username = user.username.getOrElse(Messages("guest.name"))
        Right(ConnectionService.props(supervisor, user.id, username, _: ActorRef))
      case HandlerResult(r, None) => Left(r)
    }
  }
}
