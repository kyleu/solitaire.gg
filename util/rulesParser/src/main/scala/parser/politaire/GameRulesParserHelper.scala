package parser.politaire

import parser.politaire.defaults.ParserDefaults
import parser.politaire.lookup.PolitaireLookup

trait GameRulesParserHelper extends ParserGameHelper
    with ParserStockHelper
    with ParserWasteHelper
    with ParserFoundationHelper
    with ParserTableauHelper
    with ParserCellHelper
    with ParserReserveHelper
    with ParserPyramidHelper
    with ParserSpecialHelper { this: GameRulesParser =>

  private[this] def getValue(key: String): Any = {
    variant.attributes.get(key).map(_.value).getOrElse(ParserDefaults.getDefault(key).getOrElse(throw new IllegalStateException("Missing default.")))
  }

  protected[this] def getString(key: String) = getValue(key) match {
    case s: String => s
    case i: Int => PolitaireLookup.getTranslation(key).map(_(i)).getOrElse {
      throw new IllegalArgumentException("Value [" + key + ":" + i + "] has no translation.")
    }
    case x => throw new IllegalArgumentException("Value [" + x + "] is not a string.")
  }

  protected[this] def getInt(key: String) = getValue(key) match {
    case i: Int => i
    case s: String if s == "0x0020|0x0080" || s == "0x0080|0x0020" => 160
    case s: String if s == "0x0020|0x0080" || s == "0x0080|0x0020" => 160
    case s: String if s == "2*13" || s == "13*2" => 26
    case s: String if s == "0|0|0" => 0
    case s: String if s == "1|0|0" => 1
    case s: String if s == "1|2|0" => 3
    case s: String if s == "1|0|8" => 9
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
}
