package services.wiki

import models.rules.GameRules

class WikiHelpTemplate(r: GameRules, messages: (String, Seq[Any]) => String) {
  val ret = new StringBuilder()
  def l(s: String = ""): Unit = ret.append(s + "\n")

  private[this] def rulesLink(id: String, name: String) = {
    s"[$id]$name"
  }

  def getTemplate = {
    l("# " + r.title)
    l()
    l(messages(s"rules.${r.id}.description", Nil))
    l()
    if (r.aka.nonEmpty) {
      l("## " + messages("help.also.known.as", Nil))
      l()
      r.aka.toSeq.foreach { aka =>
        l(rulesLink(aka._1, aka._2))
      }
      l()
    }
    ret.toString
  }
}
