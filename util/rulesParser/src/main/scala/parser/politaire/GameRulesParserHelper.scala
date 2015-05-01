package parser.politaire

import models.game.rules.{ InitialCards, RankMatchRule, SuitMatchRule }
import parser.politaire.defaults.ParserDefaults
import parser.politaire.lookup.PolitaireLookup

trait GameRulesParserHelper extends ParserStockHelper
    with ParserWasteHelper
    with ParserFoundationHelper
    with ParserTableauHelper
    with ParserCellHelper
    with ParserReserveHelper
    with ParserPyramidHelper { this: GameRulesParser =>

  private[this] def getValue(key: String): Any = if(overrides.isDefinedAt(key)) {
    overrides(key)
  } else {
    variant.attributes.get(key).map(_.value).getOrElse(ParserDefaults.getDefault(key).getOrElse(throw new IllegalStateException()))
  }

  protected[this] def getString(key: String) = getValue(key) match {
    case s: String => s
    case i: Int =>
      PolitaireLookup.getTranslation(key).map(_(i)).getOrElse(throw new IllegalArgumentException("Value [" + key + ":" + i + "] has no translation."))
    case x => throw new IllegalArgumentException("Value [" + x + "] is not a string.")
  }

  protected[this] def getInt(key: String) = getValue(key) match {
    case i: Int => i
    case s: String if s == "0x0020|0x0080" || s == "0x0080|0x0020" => 160
    case s: String if s == "0x0020|0x0080" || s == "0x0080|0x0020" => 160
    case s: String if s == "2*13" || s == "13*2" => 26
    case s: String if s == "2|4" => 6
    case s: String if s == "8|2" => 10
    case s: String if s == "BIT_STOCK" => 1
    case s: String if s == "BIT_WASTE" => 2
    case s: String if s == "BIT_TABLEAU" => 4
    case s: String if s == "BIT_RESERVE" => 64
    case s: String if s == "BIT_ANY & ~BIT_TABLEAU" => 251
    case s: String if s == "BIT_ANY & ~BIT_RESERVE" => 191
    case s: String if s == "BIT_STOCK|BIT_CELL" => 17
    case s: String if s == "BIT_TABLEAU|BIT_CELL" => 20
    case s: String if s == "BIT_STOCK|BIT_WASTE" => 3
    case s: String if s == "8191&(~4096)" => 4095
    case s: String if s == "1|64|128|256|512|1024|2048|4096" => 8129
    case s: String if s == "" => 0
    case s: String if s == "" => 0
    case s: String if s == "" => 0
    case s: String if s.startsWith("0x") => if (s.contains("|")) {
      throw new IllegalArgumentException("Invalid integer string [" + s + "].")
    } else {
      Integer.parseInt(s.trim().substring(2), 16)
    }
    case x => throw new IllegalArgumentException("Invalid integer [" + x + "].")
  }

  protected[this] def getBoolean(key: String) = {
    getValue(key) match {
      case i: Int => i != 0
      case b: Boolean => b
      case x => throw new IllegalArgumentException("Invalid boolean [" + x + "].")
    }
  }

  protected[this] def getInitialCards(i: Int) = i match {
    case -1 => InitialCards.PileIndex
    case -3 => InitialCards.RestOfDeck
    case -2 => InitialCards.Custom
    case x => InitialCards.Count(x)
  }

  protected[this] def getSuitMatchRule(i: Int) = i match {
    case 0 => SuitMatchRule.None
    case 1 => SuitMatchRule.SameSuit
    case 2 => SuitMatchRule.DifferentSuits
    case 3 => SuitMatchRule.SameColor
    case 4 => SuitMatchRule.AlternatingColors
    case 5 => SuitMatchRule.Any
    case x => throw new IllegalArgumentException(x.toString)
  }

  protected[this] def getRankMatchRule(i: Int) = i match {
    case 0 => RankMatchRule.None
    case 128 => RankMatchRule.Up
    case 32 => RankMatchRule.Down
    case 64 => RankMatchRule.Equal
    case 160 => RankMatchRule.UpOrDown
    case 96 => RankMatchRule.Any
    case 256 => RankMatchRule.UpBy2
    case 16 => RankMatchRule.DownBy2
    case 512 => RankMatchRule.UpBy3
    case 8 => RankMatchRule.DownBy3
    case 1024 => RankMatchRule.UpBy4
    case 4 => RankMatchRule.DownBy4
    case 8192 => RankMatchRule.UpByPileIndex
    case 8191 => RankMatchRule.Any
    case x => throw new IllegalArgumentException(x.toString)
  }
}
