package controllers

import java.util.UUID

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.SessionAuthenticator
import models.user.User
import nl.grons.metrics.scala.Timer
import play.api.libs.Crypto
import play.api.mvc._
import services.account.AccountService
import services.user.AuthenticationEnvironment
import utils.metrics.Instrumented
import utils.Logging

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object BaseController extends Instrumented {
  val timers = collection.mutable.HashMap.empty[String, Timer]

  class AuthenticatedRequest[A](val accountId: UUID, val name: String, val role: String, request: Request[A]) extends WrappedRequest[A](request) {
    def isAdmin = role == "admin"
  }

  object AuthenticatedAction extends ActionBuilder[AuthenticatedRequest] {
    override def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[Result]): Future[Result] = {
      val timer = timers.getOrElseUpdate(request.path, metrics.timer("request." + request.path.substring(1).replaceAll("\\/", "\\.")))
      timer.time {
        val fallBackAccount = request.cookies.get("solitaire_gg_account").map(c => UUID.fromString(Crypto.decryptAES(c.value)))
        AccountService.getAccount(request.session.data, fallBackAccount).flatMap {
          case (accountId, name, role, newAccount) =>
            val newRequest = new AuthenticatedRequest(accountId, name, role, request)
            if (newAccount) {
              block(newRequest).map { result =>
                result.withSession {
                  request.session + ("account" -> accountId.toString) + ("name" -> name) + ("role" -> role)
                }.withCookies(Cookie("solitaire_gg_account", Crypto.encryptAES(accountId.toString), Some(31556000 /* 1 year */ ), "/", None, secure = false))
              }
            } else {
              block(newRequest)
            }
        }
      }
    }
  }

  object AdminAction extends ActionBuilder[AuthenticatedRequest] {
    override def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[Result]): Future[Result] = {
      val timer = timers.getOrElseUpdate(request.path, metrics.timer("request." + request.path.substring(1).replaceAll("\\/", "\\.")))
      timer.time {
        val fallBackAccount = request.cookies.get("solitaire_gg_account").map(c => UUID.fromString(Crypto.decryptAES(c.value)))
        AccountService.getAccount(request.session.data, fallBackAccount).flatMap {
          case (accountId, name, role, newAccount) => if(role == "admin") {
            val newRequest = new AuthenticatedRequest(accountId, name, role, request)
            block(newRequest)
          } else {
            Future.successful(Results.Ok("You are not authorized to access this resource."))
          }
        }
      }
    }
  }
}

abstract class BaseController extends Silhouette[User, SessionAuthenticator] with Logging {
  override protected def env = AuthenticationEnvironment
}
