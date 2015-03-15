package services.game

trait GameServiceHelper
  extends GameServiceConnectionHelper
  with GameServicePossibleMovesHelper
  with GameServiceTraceHelper
  with GameServiceCardHelper
{ this: GameService =>

}
