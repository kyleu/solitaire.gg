package controllers

import services.Testbed

import scala.concurrent.Future

object SandboxController extends BaseController {
  def sandbox() = withSession( implicit request =>
    Future.successful(Ok("TODO"))
  )

  def adminSandbox = AdminAction.async { implicit request =>
    Future.successful(Ok(Testbed.go()))
  }
}
