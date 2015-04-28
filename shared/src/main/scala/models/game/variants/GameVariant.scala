package models.game.variants

import java.util.UUID

import models.game.generated.GameRulesSet

object GameVariant {
  val completed = Seq(
    "klondike"
  )
}

case class GameVariant(rulesKey: String, gameId: UUID, seed: Int) {
  val rules = GameRulesSet.allById(rulesKey)
  val initialMoves = getInitialMoves(rulesKey)

  val gameState = rules.newGame(gameId, seed)

  def performInitialMoves() = {
    initialMoves(gameState)
  }

  def isWin = {
    rules.victoryCondition.check(gameState)
  }

  private[this] def getInitialMoves(rulesKey: String) = rulesKey match {
    case "canfield" => Canfield.initialMoves _
    case "freecell" => FreeCell.initialMoves _
    case "golf" => Golf.initialMoves _
    case "gypsy" => Gypsy.initialMoves _
    case "klondike" => Klondike.initialMoves _
    case "nestor" => Nestor.initialMoves _
    case "pyramid" => Pyramid.initialMoves _
    case "sandbox" => Sandbox.initialMoves _
    case "sandboxb" => SandboxB.initialMoves _
    case "spider" => Spider.initialMoves _
    case "trustytwelve" => TrustyTwelve.initialMoves _
    case "yukon" => Yukon.initialMoves _
    case x => throw new NotImplementedError("Unimplemented game [" + x + "].")
  }
}
