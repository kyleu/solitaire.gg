package controllers

import models.user.{Role, WithRole}
import play.api.mvc.Action
import services.Testbed

import scala.concurrent.Future

object SandboxController extends BaseController {
  def sandbox() = Action.async {
    Future.successful(Ok("OK!"))
  }

  def adminSandbox = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future.successful(Ok(Testbed.go()))
  }
}
