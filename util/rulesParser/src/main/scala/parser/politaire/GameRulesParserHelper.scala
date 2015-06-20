package parser.politaire

import parser.politaire.defaults.ParserDefaults
import parser.politaire.lookup.PolitaireLookup
import parser.utils.IntUtils

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
      throw new IllegalArgumentException(s"Value [$key:$i] has no translation.")
    }
    case x => throw new IllegalArgumentException(s"Value [$x] is not a string.")
  }

  protected[this] def getInt(key: String) = getValue(key) match {
    case i: Int => i
    case s: String => IntUtils.parse(s).getOrElse(throw new IllegalArgumentException(s"Invalid integer [$s]."))
    case x => throw new IllegalArgumentException(s"Invalid integer [$x].")
  }

  protected[this] def getBoolean(key: String) = {
    getValue(key) match {
      case i: Int => i != 0
      case b: Boolean => b
      case x => throw new IllegalArgumentException(s"Invalid boolean [$x].")
    }
  }
}
