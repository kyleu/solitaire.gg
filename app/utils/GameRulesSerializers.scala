package utils

import models.game.rules._
import play.api.libs.json._

object GameRulesSerializers {
  import GameSerializers._

  implicit val victoryConditionWrites = new Writes[VictoryCondition] {
    override def writes(vc: VictoryCondition) = JsString(vc.toString)
  }

  implicit val cardRemovalMethodWrites = new Writes[CardRemovalMethod] {
    override def writes(cr: CardRemovalMethod) = JsString(cr.toString)
  }

  implicit val deckOptionsWrites = Json.writes[DeckOptions]

  implicit val foundationLowRankWrites = new Writes[FoundationLowRank] {
    override def writes(flr: FoundationLowRank) = JsString(flr.toString)
  }
  implicit val suitMatchRuleWrites = new Writes[SuitMatchRule] {
    override def writes(smr: SuitMatchRule) = JsString(smr.toString)
  }
  implicit val rankMatchRuleWrites = new Writes[RankMatchRule] {
    override def writes(rmr: RankMatchRule) = JsString(rmr.toString)
  }

  implicit val foundationSetWrites = Json.writes[FoundationSet]

  implicit val gameRulesWrites = Json.writes[GameRules]
}
