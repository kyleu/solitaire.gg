package util

object Formatter {
  private[this] val numFormatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.US)

  def className(x: AnyRef) = x.getClass.getSimpleName.stripSuffix("$")

  def withCommas(i: Int) = numFormatter.format(i.toLong)
  def withCommas(l: Long) = numFormatter.format(l)
  def withCommas(d: Double) = numFormatter.format(d)
}
