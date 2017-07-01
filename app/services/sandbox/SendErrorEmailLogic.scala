package services.sandbox

import utils.Application

import scala.concurrent.Future

trait SendErrorEmailLogic {
  def call(ctx: Application) = {
    Future.successful("Ok!")
  }
}
