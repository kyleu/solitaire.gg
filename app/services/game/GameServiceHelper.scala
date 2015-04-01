package services.game

import utils.Logging
import utils.metrics.InstrumentedActor

trait GameServiceHelper
    extends InstrumentedActor
    with Logging
    with GameServiceCardHelper
    with GameServiceConnectionHelper
    with GameServicePossibleMovesHelper
    with GameServiceTraceHelper { this: GameService =>

}
