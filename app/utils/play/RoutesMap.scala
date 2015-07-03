package utils.play

object RoutesMap {
  private[this] def first(args: Seq[String]) = args.headOption.getOrElse(throw new IllegalStateException("Missing argument."))

  val static = RoutesMap(Map(
    ("css-root", (args: Seq[String]) => "assets/stylesheets/" + first(args)),
    ("script-root", (args: Seq[String]) => "assets/javascripts/" + first(args)),
    ("image-root", (args: Seq[String]) => "assets/images/" + first(args)),

    ("index", (args: Seq[String]) => "index.html"),
    ("about", (args: Seq[String]) => "about.html"),
    ("profile", (args: Seq[String]) => "profile.html"),

    ("help", (args: Seq[String]) => "help/" + first(args) + ".html"),
    ("new-game", (args: Seq[String]) => "gameplay.html?rules=" + first(args))
  ), dynamic = false)

  val dynamic = RoutesMap(Map(
    ("css-root", (args: Seq[String]) => "/assets/stylesheets/" + first(args)),
    ("script-root", (args: Seq[String]) => "/assets/javascripts/" + first(args)),
    ("image-root", (args: Seq[String]) => "/assets/images/" + first(args)),

    ("index", (args: Seq[String]) => controllers.routes.HomeController.index().url),
    ("about", (args: Seq[String]) => controllers.routes.HomeController.about().url),
    ("profile", (args: Seq[String]) => controllers.routes.ProfileController.profile().url),

    ("help", (args: Seq[String]) => controllers.routes.GameController.help(first(args)).url),
    ("new-game", (args: Seq[String]) => controllers.routes.GameController.newGame(first(args)).url)
  ), dynamic = true)
}

case class RoutesMap(routes: Map[String, (Seq[String] => String)], dynamic: Boolean) {
  def apply(path: String): String = {
    routes(path)(Seq.empty)
  }

  def apply(path: String, param: String): String = {
    routes(path)(Seq(param))
  }

  def apply(path: String, params: Seq[String]): String = {
    routes(path)(params)
  }
}
