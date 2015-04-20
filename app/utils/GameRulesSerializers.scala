package utils

import models.game.rules._
import play.api.libs.json._

object GameRulesSerializers {
  import GameSerializers._

  def stringWriter[T] = new Writes[T] {
    override def writes(t: T) = JsString(t.toString)
  }

  implicit val victoryConditionWrites = stringWriter[VictoryCondition]
  implicit val cardRemovalMethodWrites = stringWriter[CardRemovalMethod]

  implicit val deckOptionsWrites = Json.writes[DeckOptions]

  implicit val initialCardsWrites = stringWriter[InitialCards]

  // Foundation
  implicit val foundationCanMoveFromWrites = stringWriter[FoundationCanMoveFrom]
  implicit val foundationLowRankWrites = stringWriter[FoundationLowRank]
  implicit val suitMatchRuleWrites = stringWriter[SuitMatchRule]
  implicit val rankMatchRuleWrites = stringWriter[RankMatchRule]

  implicit val foundationSetWrites = Json.writes[FoundationSet]

  // Tableau
  implicit val tableauFaceDownCardsWrites = stringWriter[TableauFaceDownCards]
  implicit val tableauAutoFillEmptyFromWrites = stringWriter[TableauAutoFillEmptyFrom]
  implicit val tableauFillEmptyWithWrites = stringWriter[TableauFillEmptyWith]
  implicit val pileActionWrites = stringWriter[PileAction]

  implicit val tableauSetWrites = Json.writes[TableauSet]

  implicit val gameRulesWrites = Json.writes[GameRules]
}
