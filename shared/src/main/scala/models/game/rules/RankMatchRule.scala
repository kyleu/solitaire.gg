package models.game.rules

sealed trait RankMatchRule

object RankMatchRule {
  case object None extends RankMatchRule
  case object Up extends RankMatchRule
  case object Down extends RankMatchRule
  case object Equal extends RankMatchRule
  case object UpOrDown extends RankMatchRule
  case object UpBy2 extends RankMatchRule
  case object DownBy2 extends RankMatchRule
  case object UpBy3 extends RankMatchRule
  case object DownBy3 extends RankMatchRule
  case object UpBy4 extends RankMatchRule
  case object DownBy4 extends RankMatchRule
  case object UpByPileIndex extends RankMatchRule
  case object Any extends RankMatchRule
}
