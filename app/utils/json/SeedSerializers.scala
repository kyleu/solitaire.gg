package utils.json

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import io.circe.parser._
import io.circe.syntax._
import models.history.GameSeed

import utils.json.JodaSerializers.localDateTimeFormat

object SeedSerializers {
  private[this] implicit val config = Configuration.default.withDefaults

  def writeSeed(rules: GameSeed) = rules.asJson.noSpaces

  def readSeed(s: String) = decode[GameSeed](s) match {
    case Right(x) => x
    case Left(err) => throw err
  }
}
