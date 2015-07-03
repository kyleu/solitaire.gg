package utils.play

object RoutesMap {
  val static = RoutesMap(Map(
    ("css-root", (args: Seq[String]) => "assets/stylesheets/" + args.head),
    ("script-root", (args: Seq[String]) => "assets/javascripts/" + args.head),
    ("image-root", (args: Seq[String]) => "assets/images/" + args.head),

    ("index", (args: Seq[String]) => "index.html"),
    ("about", (args: Seq[String]) => "about.html"),
    ("profile", (args: Seq[String]) => "profile.html"),

    ("help", (args: Seq[String]) => "help/" + args.head + ".html"),
    ("new-game", (args: Seq[String]) => "gameplay.html?rules=" + args.head)
  ), dynamic = false)

  val dynamic = RoutesMap(Map(
    ("css-root", (args: Seq[String]) => "/assets/stylesheets/" + args.head),
    ("script-root", (args: Seq[String]) => "/assets/javascripts/" + args.head),
    ("image-root", (args: Seq[String]) => "/assets/images/" + args.head),

    ("index", (args: Seq[String]) => controllers.routes.HomeController.index().url),
    ("about", (args: Seq[String]) => controllers.routes.HomeController.about().url),
    ("profile", (args: Seq[String]) => controllers.routes.ProfileController.profile().url),

    ("help", (args: Seq[String]) => controllers.routes.GameController.help(args.head).url),
    ("new-game", (args: Seq[String]) => controllers.routes.GameController.newGame(args.head).url)
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
