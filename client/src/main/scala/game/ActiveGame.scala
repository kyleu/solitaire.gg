package game

import java.util.UUID

import models.rules.GameRulesSet

import scala.util.Random

case class ActiveGame(id: UUID = UUID.randomUUID, rules: String = "klondike", seed: Int = Random.nextInt) {
  val gameRules = GameRulesSet.allByIdWithAliases(rules)

  lazy val gameState = gameRules.newGame(id, seed, rules)
}
