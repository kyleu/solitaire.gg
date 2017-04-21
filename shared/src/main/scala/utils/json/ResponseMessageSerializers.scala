package utils.json

import models._
import upickle._
import upickle.legacy._
import GameSerializers._

object ResponseMessageSerializers {
  def read(json: Js.Value) = readJs[ResponseMessage](json)
  def write(rm: ResponseMessage) = writeJs(rm)
}
