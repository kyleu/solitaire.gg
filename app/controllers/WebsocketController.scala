package controllers

import models.{RequestMessage, ResponseMessage}
import play.api.mvc.{Controller, WebSocket}
import services.{ActorSupervisor, ConnectionService}

import scala.util.Random

object WebsocketController extends Controller {
  import utils.MessageFrameFormatter._
  import play.api.Play.current

  val supervisor = ActorSupervisor.instance

  def connect() = WebSocket.acceptWithActor[RequestMessage, ResponseMessage] { request => out =>
    val username = request.session.get("username").getOrElse("guest-" + Random.nextInt(10000))
    ConnectionService.props(supervisor, username, out)
  }
}
