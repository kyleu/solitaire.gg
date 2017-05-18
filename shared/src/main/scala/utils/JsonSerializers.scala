package utils

import models.settings.Settings
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import models.rules.GameRules
import msg.req.SocketRequestMessage
import msg.rsp.SocketResponseMessage

object JsonSerializers {
  def readSettings(s: String) = decode[Settings](s) match {
    case Right(x) => x
    case Left(err) => Settings.default
  }
  def writeSettings(s: Settings, indent: Boolean = true) = if(indent) { s.asJson.spaces2 } else { s.asJson.noSpaces }

  def writeRules(rules: GameRules) = rules.asJson.noSpaces

  def readRequestMessage(s: String) = decode[SocketRequestMessage](s) match {
    case Right(x) => x
    case Left(err) => throw err
  }
  def writeRequestMessage(sm: SocketRequestMessage, debug: Boolean = false) = if (debug) { sm.asJson.spaces2 } else { sm.asJson.noSpaces }

  def readResponseMessage(s: String) = decode[SocketResponseMessage](s) match {
    case Right(x) => x
    case Left(err) => throw err
  }
  def writeResponseMessage(sm: SocketResponseMessage, debug: Boolean = false) = if (debug) { sm.asJson.spaces2 } else { sm.asJson.noSpaces }

}
