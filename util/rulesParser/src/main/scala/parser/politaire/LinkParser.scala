package parser.politaire

import scala.io.Source

object LinkParser {
  def parse() = {
    val body = Source.fromFile("../politaire-help").getLines().toList

    val ret = body.flatMap { line =>
      val split = line.split(":")
      val key = split(0)
      val links = split(1).split(",")
      Some(key -> links.flatMap { link =>
        if(link.contains(' ')) {
          val p = link.substring(0, link.indexOf(" "))
          val h = link.substring(link.indexOf(' ') + 1)
          val (host, page) = lookupLink(h, p)
          Some(host -> page)
        } else {
          None
        }
      })
    }

    ret
  }

  private[this] def lookupLink(host: String, page: String) = host match {
    case "P" => "Pretty Good Solitaire" -> s"www.goodsol.com/pgshelp/$page.htm"
    case "A" => "AisleRiot" -> s"help.gnome.org/users/aisleriot/stable/$page.html.en"
    case "W" => "Wikipedia" -> s"en.wikipedia.org/wiki/$page"
    case "BVS" => "BVS Solitaire Collection" -> s"www.bvssolitaire.com/rules/$page.htm"
    case "LC" => "Lady Cadogan's Illustrated Games of Solitaire or Patience" -> s"www.gutenberg.org/files/21642/21642-h/21642-h.htm#$page"
    case "STD" => "Solitaire Till Dawn" -> s"www.semicolon.com/Solitaire/Rules/$page.html"
    case "SS" => "Solsuite Solitaire" -> s"www.solsuite.com/games/$page.htm"
    case "Sv" => "Solavant Solitaire" -> s"www.solavant.com/solitaire/$page.php"
    case "R" => "Rapture Technologies KingSol" -> s"www.rapturetech.com/KingSol/Rules/$page.htm"
    case "SC" => "Solitaire Central" -> s"www.solitairecentral.com/rules/$page.html"
    case "X" => "Xolitaire" -> s"www.escapedivision.com/xolitaire/en/games/$page.html"
    case "D" => "dogMelon" -> s"www.dogmelon.com.au/solhelp/$page.shtml"
    case "SGR" => "Solitaire Game Rules.com" -> s"solitaire-game-rules.com/games/$page.htm"
    case "Z" => "Zonora" -> s"www.zonora.com/mysolitaire/rules/$page.htm"
    case "Py" => "PySol" -> s"pysolfc.sourceforge.net/doc/rules/$page.html"
    case _ => host -> page
  }
}
