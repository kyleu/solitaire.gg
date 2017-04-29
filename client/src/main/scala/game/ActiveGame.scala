package game

import java.util.UUID

import models.rules.GameRulesSet

import scala.util.Random

case class ActiveGame(id: UUID = UUID.randomUUID, rulesId: String = "klondike", seed: Int = Random.nextInt) {
  val rules = GameRulesSet.allByIdWithAliases(rulesId)
  val state = rules.newGame(id, seed, rulesId)
}
