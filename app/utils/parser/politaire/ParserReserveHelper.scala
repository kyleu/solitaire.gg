package utils.parser.politaire

import models.game.rules.ReserveSet

trait ParserReserveHelper { this: GameRulesParser =>
  protected[this] def getReserves = {
    getInt("R0n") match {
      case 0 => None
      case numPiles =>
        Some(ReserveSet(
          name = getString("R0Nm"),
          numPiles = numPiles,
          initialCards = getInt("R0d"),
          cardsFaceDown = getInt("R0df")
        ))
    }
  }
}
