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
      case _ => throw new NotImplementedError()
    }
    if(properCase) {
      ret.head.toUpper + ret.tail
    } else {
      ret
    }
  }
}
