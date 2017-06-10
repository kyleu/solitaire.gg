package utils.json

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import io.circe.syntax._
import models.history.GameSeed

object SeedSerializers {
  private[this] implicit val config = Configuration.default.withDefaults

  def writeSeed(rules: GameSeed) = rules.asJson.noSpaces
}
