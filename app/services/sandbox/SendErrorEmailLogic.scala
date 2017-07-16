package services.sandbox

import util.Application

import scala.concurrent.Future

trait SendErrorEmailLogic {
  def call(ctx: Application) = {
    Future.successful("Ok!")
  }
}
