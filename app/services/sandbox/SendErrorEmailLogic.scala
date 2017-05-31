package services.sandbox

import utils.Application

import scala.concurrent.Future

trait SendErrorEmailLogic {
  def run(ctx: Application) = {
    Future.successful("Ok!")
  }
}
