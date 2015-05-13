package controllers

import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.impl.authenticators.SessionAuthenticator
import models.user.User
import nl.grons.metrics.scala.Timer
import services.user.AuthenticationEnvironment
import utils.Logging
import utils.metrics.Instrumented

object BaseController extends Instrumented {
  val timers = collection.mutable.HashMap.empty[String, Timer]
}

abstract class BaseController extends Silhouette[User, SessionAuthenticator] with Instrumented with Logging {
  override protected def env = AuthenticationEnvironment
}
