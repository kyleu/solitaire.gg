package util

import enumeratum.values.StringEnumEntry

abstract class EnumWithDescription() extends StringEnumEntry {
  def value: String
  def description: String
  override def toString = value
}
