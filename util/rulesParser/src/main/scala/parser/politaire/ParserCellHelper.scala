package parser.politaire

import models.game.rules.CellRules
import parser.politaire.lookup.PolitaireLookup

trait ParserCellHelper { this: GameRulesParser =>
  protected[this] def getCells = {
    getInt("C0n") match {
      case 0 => None
      case numPiles =>
        Some(CellRules(
          name = getString("C0Ns"),
          pluralName = getString("C0Nm"),
          numPiles = numPiles,
          mayMoveToFrom = PolitaireLookup.parseBitmask("C0o", getInt("C0o")),
          initialCards = getInt("C0d"),
          numEphemeral = getInt("C0e")
        ))
    }
  }
}
