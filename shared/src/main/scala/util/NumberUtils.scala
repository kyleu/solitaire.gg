package util

object NumberUtils {
  def withCommas(i: Int): String = i.toString.reverse.grouped(3).mkString(",").reverse
  def withCommas(l: Long): String = l.toString.reverse.grouped(3).mkString(",").reverse
  def withCommas(d: Double): String = d.toString.split('.').toList match {
    case h :: t :: Nil => withCommas(h.toLong) + "." + t
    case h :: Nil => withCommas(h.toLong)
    case _ => d.toString
  }
}
