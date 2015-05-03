package services

import models.game.rules.{ DeckOptions, CardRemovalMethod, VictoryCondition }
import utils.NumberUtils

object RulesHelpService {
  def description(desc: String) = {
    val ret = desc.replaceAllLiterally(" ^", " <strong>").replaceAllLiterally("^", "</strong>")
    ret
  }

  def objective(vc: VictoryCondition, crm: CardRemovalMethod) = vc match {
    case VictoryCondition.AllButFourCardsOnFoundation => "Place all but four cards on the foundation."
    case VictoryCondition.AllOnFoundation => "Place all cards on the foundation."
    case VictoryCondition.AllOnFoundationOrStock => "Place all cards on the foundation or stock."
    case VictoryCondition.AllOnTableauSorted => "Sort all cards on the tableau."
    case VictoryCondition.NoneInPyramid => "Remove all cards from the pyramid."
    case VictoryCondition.NoneInStock => "Remove all cards from the stock."
  }

  def deck(options: DeckOptions) = {
    NumberUtils.toWords(options.numDecks, properCase = true) + " standard deck" + (if(options.numDecks != 1) { "s." } else { "." })
  }
}
