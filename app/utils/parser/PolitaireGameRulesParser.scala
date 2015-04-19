package utils.parser

import models.game._

class PolitaireGameRulesParser(variant: PolitaireParser.Variant) {
  def parse() = {
    val lowRank = {
      val lowChar = getString("Low card").headOption.getOrElse(throw new IllegalStateException())
      if(lowChar == '.') { None } else { Some(Rank.fromChar(lowChar)) }
    }

    val deckOptions = DeckOptions(
      numDecks = getInt("Number of decks"),
      suits = Suit.all,
      ranks = Rank.all,
      lowRank = lowRank
    )

    val foundations = Nil

    GameRules(
      id = variant.id,
      title = getString("Title"),
      description = variant.attributes("Description")._1.toString,
      victoryCondition = VictoryCondition(),
      cardRemovalMethod = CardRemovalMethod(),
      deckOptions = deckOptions,
      foundations = foundations
    )
  }

  private[this] def getAttribute(key: String): (Any, Option[String]) = {
    val attr = variant.attributes(key)
    attr._1 -> attr._2
  }
  private[this] def getString(key: String) = {
    val attr = getAttribute(key)
    attr._2.getOrElse(attr._1 match {
      case s: String => s
      case x => throw new IllegalArgumentException("Value [" + x + "] is not a string.")
    })
  }
  private[this] def getInt(key: String) = getAttribute(key)._1 match {
    case i: Int => i
    case x => throw new IllegalArgumentException("Invalid integer [" + x + "].")
  }
}
