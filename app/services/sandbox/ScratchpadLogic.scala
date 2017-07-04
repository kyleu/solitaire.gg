package services.sandbox

import utils.Application

import scala.concurrent.Future

trait ScratchpadLogic {
  def call(ctx: Application) = {
    Future.successful("OK")
  }
}
