package services.sandbox

import util.Application

import scala.concurrent.Future

trait ScratchpadLogic {
  def call(ctx: Application) = {
    Future.successful("OK")
  }
}
