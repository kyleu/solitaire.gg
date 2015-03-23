package services.game

import utils.Logging
import utils.metrics.InstrumentedActor

trait GameServiceHelper
  extends InstrumentedActor
  with Logging
  with GameServiceConnectionHelper
  with GameServicePossibleMovesHelper
  with GameServiceTraceHelper
  with GameServiceCardHelper
{ this: GameService =>

}
