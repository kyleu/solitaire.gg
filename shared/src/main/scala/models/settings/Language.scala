package models.settings

import enumeratum.values._
import utils.EnumWithDescription

sealed abstract class Language(override val value: String, override val description: String) extends EnumWithDescription

object Language extends StringEnum[Language] {
  case object English extends Language("en", "English")
  case object French extends Language("fr", "French")
  case object German extends Language("de", "German")
  case object Italian extends Language("it", "Italian")
  case object Spanish extends Language("es", "Spanish")
  case object Portugeuse extends Language("pt", "Portugeuse")
  case object Polish extends Language("pl", "Polish")
  case object Dutch extends Language("nl", "Dutch")
  case object Swedish extends Language("sv", "Swedish")

  case object Chinese extends Language("zh", "Chinese")
  case object Japanese extends Language("ja", "Japanese")
  case object Korean extends Language("ko", "Korean")
  case object Thai extends Language("th", "Thai")
  case object Vietnamese extends Language("vi", "Vietnamese")

  case object Croation extends Language("hr", "Croation")
  case object Czech extends Language("cs", "Czech")
  case object Hebrew extends Language("iw", "Hebrew")
  case object Hindi extends Language("hi", "Hindi")
  case object Arabic extends Language("ar", "Arabic")

  override val values = findValues
}
