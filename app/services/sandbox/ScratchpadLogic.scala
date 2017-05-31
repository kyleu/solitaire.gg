package services.sandbox

import utils.Application

import scala.concurrent.Future

trait ScratchpadLogic {
  def run(ctx: Application) = {
    val ret = "OK"
    Future.successful(ret)
  }
}
