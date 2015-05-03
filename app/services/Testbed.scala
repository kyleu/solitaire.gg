package services

import models.game.rules.GameRulesSet
import play.api.mvc.{ Flash, Session }

object Testbed {
  def go()(implicit session: Session, flash: Flash) = {
    views.html.admin.helpTest(GameRulesSet.all)
  }
}
