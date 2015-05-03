package utils

object NumberUtils {
  def toWords(i: Int, properCase: Boolean = false) = {
    val ret = i match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case 4 => "four"
      case 5 => "five"
      case 6 => "six"
      case 7 => "seven"
      case 8 => "eight"
      case 9 => "nine"
      case 10 => "ten"
      case 11 => "eleven"
      case 12 => "twelve"
      case 13 => "thirteen"
      case 14 => "fourteen"
      case 15 => "fifteen"
      case 16 => "sixteen"
      case 17 => "seventeen"
      case 18 => "eightteen"
      case 19 => "nineteen"
      case i => throw new NotImplementedError(i.toString)
    }
    if(properCase) {
      ret.head.toUpper + ret.tail
    } else {
      ret
    }
  }
}
