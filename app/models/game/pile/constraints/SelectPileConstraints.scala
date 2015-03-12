package models.game.pile.constraints

import models.game.pile.Pile

object SelectPileConstraints {
  def never(pile: Pile) = false
  def always(pile: Pile) = true
  def empty(pile: Pile) = pile.cards.isEmpty

  def apply(key: Option[String]) = key match {
    case Some(k) => k match {
      case "always" => always _
      case "empty" => empty _
      case _ => throw new IllegalArgumentException("Invalid select card constraint [" + k + "].")
    }
    case None => never _
  }
}
