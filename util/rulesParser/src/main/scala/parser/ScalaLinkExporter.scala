package parser

import models.rules._
import parser.politaire.LinkParser

object ScalaLinkExporter {
  lazy val links = LinkParser.parse().toMap

  def relatedGames(rules: GameRules, add: (String) => Unit) = {
    val rel = rules.related.grouped(8).toSeq
    if (rel.size == 1) {
      add("  related = Seq(" + rel.headOption.getOrElse(Nil).map("\"" + _ + "\"").mkString(", ") + "),")
    } else if (rel.size > 1) {
      add("  related = Seq(")
      for(relSet <- rel.zipWithIndex) {
        add("    " + relSet._1.map("\"" + _ + "\"").mkString(", ") + (if(relSet._2 < rel.size - 1) { "," } else { "" }))
      }
      add("  ),")
    }
  }

  def webLinks(id: String, add: (String) => Unit) = {
    val l = links.getOrElse(id, Array.empty[(String, String)]).map(x => x._1.replaceAllLiterally("\"", "\\\"") -> x._2.replaceAllLiterally("\"", "\\\""))
    if (l.length == 1) {
      val head = l.headOption.getOrElse("" -> "")
      add("  links = Seq(Link(\"" + head._1 + "\", \"" + head._2 + "\")),")
    } else if (l.length > 1) {
      add("  links = Seq(")
      for(ln <- l.zipWithIndex) {
        add("    Link(\"" + ln._1._1 + "\", \"" + ln._1._2 + "\")" + (if(ln._2 < l.length - 1) { "," } else { "" }))
      }
      add("  ),")
    }
  }
}
