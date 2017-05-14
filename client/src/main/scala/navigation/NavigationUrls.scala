package navigation

object NavigationUrls {
  val help = "/help"
  def rules(k: String) = s"/help/$k"
  def play(k: String) = s"/play/$k"
}
