package parser

import models.game.rules._

object ScalaDeckOptionsExporter {
  private[this] val defaults = DeckOptions()

  def exportDeckOptions(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    if(rules.deckOptions != defaults) {
      val props = collection.mutable.ArrayBuffer.empty[String]
      if(rules.deckOptions.numDecks != defaults.numDecks) {
        props += "    numDecks = " + rules.deckOptions.numDecks
      }
      if(rules.deckOptions.suits!= defaults.suits) {
        props += "    suits = Seq(" + rules.deckOptions.suits.map(x => "Suit." + x).mkString(", ") + ")"
      }
      if(rules.deckOptions.ranks != defaults.ranks) {
        props += "    ranks = Seq(" + rules.deckOptions.ranks.map(x => "Rank." + x).mkString(", ") + ")"
      }
      if(rules.deckOptions.lowRank != defaults.lowRank) {
        props += "    lowRank = Rank." + rules.deckOptions.lowRank
      }

      if(props.isEmpty) {
        add("  deckOptions = DeckOptions(),")
      } else {
        add("  deckOptions = DeckOptions(")
        add(props.mkString(",\n"))
        add("  ),")
      }
    }
  }
}
