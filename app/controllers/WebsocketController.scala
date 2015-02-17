package controllers

import models.{RequestMessage, ResponseMessage}
import play.api.Play.current
import play.api.mvc.{Controller, WebSocket}
import services.ConnectionService
import utils.MessageFrameFormatter

object WebsocketController extends Controller {
  import utils.MessageFrameFormatter._

  def connect() = WebSocket.acceptWithActor[RequestMessage, ResponseMessage] { request => out =>
    ConnectionService.props(out)
  }
}
