package utils.json

import models._
import upickle._
import upickle.legacy._

import BaseSerializers._
import GameSerializers._

object RequestMessageSerializers {
  def read(json: Js.Value) = readJs[RequestMessage](json)
  def write(rm: RequestMessage) = writeJs(rm)
}
