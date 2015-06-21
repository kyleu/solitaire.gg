package models.audit

import models.game.rules.GameRules

case class RulesStatus(
  rules: GameRules,

  gameCount: Int,
  gameWins: Int,

  winningSeeds: Int,
  maxMoves: Int,
  avgMoves: Int,
  minMoves: Int,

  completed: Boolean,
  inProgress: Boolean
)
