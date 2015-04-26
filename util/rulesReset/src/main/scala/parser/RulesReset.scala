package parser

import java.nio.file.{ Files, Path, Paths }

object RulesReset extends App {
  val startMs = System.currentTimeMillis
  println("Resetting game rules...")
  go()
  println("Reset complete in [" + (System.currentTimeMillis - startMs) + "ms].")

  def go() = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "generated")
    val dStream = Files.newDirectoryStream(srcDir)
    val itr = collection.JavaConverters.asScalaIteratorConverter(dStream.iterator()).asScala
    for(f <- itr) {
      Files.delete(f)
    }
    writeFile(srcDir.resolve("GameRulesSet.scala"), exportRulesSet())
  }

  def exportRulesSet() = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.generated")
    add("")
    add("import models.game.rules.custom.CustomRulesSet")
    add("")
    add("object GameRulesSet {")
    add("  val all = CustomRulesSet.all")
    add("  val allById = all.map(x => x.id -> x).toMap")
    add("}")
    add("")

    ret.toString()
  }

  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
