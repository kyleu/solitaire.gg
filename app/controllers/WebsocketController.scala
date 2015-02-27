package controllers

import models.{RequestMessage, ResponseMessage}
import play.api.mvc.{Controller, WebSocket}
import services.{ActorSupervisor, ConnectionService}

object WebsocketController extends Controller {
  import utils.MessageFrameFormatter._
  import play.api.Play.current

  val supervisor = ActorSupervisor.instance

  def connect() = WebSocket.acceptWithActor[RequestMessage, ResponseMessage] { request => out =>
    ConnectionService.props(supervisor, out)
  }
}
