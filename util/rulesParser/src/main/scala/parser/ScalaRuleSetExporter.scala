package parser

import models.game.rules._

object ScalaRuleSetExporter {
  def exportRulesSet(rulesSet: Seq[GameRules]) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.rules.impl")
    add("")
    add("// scalastyle:off")
    add("object GeneratedGameRules {")
    add("  val all = Seq(")
    add(rulesSet.map(r => "    " + getObjectName(r)).mkString(",\n"))
    add("  )")
    add("}")
    add("// scalastyle:on")
    add("")

    ret.toString()
  }

  private def getObjectName(rules: GameRules) = rules.title.replaceAll("[-'\\(\\)]", "").split(" ").map(x => x.head.toUpper + x.tail).mkString
}
