package controllers

import services.Testbed
import utils.CacheService

import scala.concurrent.Future

object SandboxController extends BaseController {
  def sandbox() = withSession { implicit request =>
    Future.successful(Ok(CacheService.keys().mkString("\n")))
  }

  def adminSandbox = AdminAction.async { implicit request =>
    Future.successful(Ok(Testbed.go()))
  }
}
