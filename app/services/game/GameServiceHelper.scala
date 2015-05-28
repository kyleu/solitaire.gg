package services.game

import utils.Logging
import utils.metrics.InstrumentedActor

trait GameServiceHelper
    extends InstrumentedActor
    with Logging
    with GameServiceCardHelper
    with GameServiceConnectionHelper
    with GameServiceMessageHelper
    with GameServicePersistenceHelper
    with GameServicePossibleMovesHelper
    with GameServiceTraceHelper
    with GameServiceUndoHelper { this: GameService =>
}
