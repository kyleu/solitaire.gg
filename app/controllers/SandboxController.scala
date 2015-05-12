package controllers

import controllers.BaseController.AdminAction
import play.api.mvc.Action
import services.Testbed

import scala.concurrent.Future

object SandboxController extends BaseController {
  def sandbox() = Action.async {
    Future.successful(Ok("OK!"))
  }

  def adminSandbox = AdminAction.async { implicit request =>
    Future.successful(Ok(Testbed.go()))
  }
}
