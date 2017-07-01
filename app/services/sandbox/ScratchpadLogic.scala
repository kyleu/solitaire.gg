package services.sandbox

import utils.Application

import scala.concurrent.Future

trait ScratchpadLogic {
  def call(ctx: Application) = {
    val ret = "OK"
    Future.successful(ret)
  }
}
