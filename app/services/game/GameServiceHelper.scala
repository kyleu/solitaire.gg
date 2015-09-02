package services.game

import org.joda.time.LocalDateTime
import utils.{ DateUtils, Logging }
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
    with GameServicePreferenceHelper
    with GameServiceScoreHelper
    with GameServiceTraceHelper
    with GameServiceUndoHelper
    with GameServiceVictoryHelper { this: GameService =>

  protected[this] def elapsedMs = firstMoveMade match {
    case Some(fmm) => DateUtils.toMillis(new LocalDateTime()).toInt
    case None => 0
  }
}
