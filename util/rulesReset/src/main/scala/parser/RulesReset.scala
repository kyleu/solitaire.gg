package parser

import java.nio.file.{ Files, Path, Paths }

object RulesReset extends App {
  val startMs = System.currentTimeMillis
  println("Resetting game rules...")
  go()
  println(s"Reset complete in [${System.currentTimeMillis - startMs}ms].")

  def go() = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "rules", "impl")
    val dStream = Files.newDirectoryStream(srcDir)
    val itr = collection.JavaConverters.asScalaIteratorConverter(dStream.iterator()).asScala
    for(f <- itr) {
      Files.delete(f)
    }
    writeFile(srcDir.resolve("GeneratedGameRules.scala"), exportRulesSet())
  }

  def exportRulesSet() = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.rules.impl")
    add("")
    add("object GeneratedGameRules {")
    add("  val all = Nil")
    add("}")
    add("")

    ret.toString()
  }

  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
