package controllers

import models.game.rules.GameRulesSet
import utils.CacheService

import scala.concurrent.Future

object SandboxController extends BaseController {
  def sandbox() = withSession { implicit request =>
    Future.successful(Ok(CacheService.keys().mkString("\n")))
  }

  def adminSandbox = withAdminSession { implicit request =>
    val ret = views.html.admin.sandbox(GameRulesSet.all)
    Future.successful(Ok(ret))
  }
}
