package services.game

import utils.Logging
import utils.metrics.InstrumentedActor

trait GameServiceHelper
    extends InstrumentedActor
    with Logging
    with GameServiceCardHelper
    with GameServiceCheatHelper
    with GameServiceCompletionHelper
    with GameServiceConnectionHelper
    with GameServiceMessageHelper
    with GameServicePersistenceHelper
    with GameServicePossibleMovesHelper
    with GameServiceTraceHelper
    with GameServiceUndoHelper
    with GameServiceVictoryHelper { this: GameService =>

  protected[this] def handleSetPreference(name: String, value: String) = name match {
    case "auto-flip" => gameState.setAutoFlipOption(value == "true")
    case _ => // no op
  }
}
