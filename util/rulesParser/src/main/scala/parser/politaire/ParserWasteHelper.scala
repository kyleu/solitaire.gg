package parser.politaire

import models.game.rules.{ WastePlayableCards, WasteRules }

trait ParserWasteHelper { this: GameRulesParser =>
  protected[this] def getWaste = {
    getInt("W0n") match {
      case 0 => None
      case numPiles =>
        Some(WasteRules(
          name = getString("W0Nm"),
          numPiles = numPiles,
          cardsShown = getInt("W0cardsShown"),
          playableCards = try {
            getInt("W0a") match {
              case 0 => WastePlayableCards.TopCardOnly
              case 1 => WastePlayableCards.All
            }
          } catch {
            case x: IllegalArgumentException => getBoolean("W0a") match {
              case true => WastePlayableCards.TopCardOnly
              case false => WastePlayableCards.All
            }
          }
        ))
    }
  }
}
