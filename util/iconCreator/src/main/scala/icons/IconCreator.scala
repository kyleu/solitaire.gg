package icons

import java.nio.file.{ Files, Path, Paths }

object IconCreator extends App {
  private val icons = Seq("back", "next", "hint", "options", "profile", "menu", "help", "edit", "cards", "check", "error")
  private val colors = Seq(
    "white" -> "ffffff",
    "black" -> "000000",
    "red" -> "f44336",
    "maroon" -> "e91e63",
    "indigo" -> "9c27b0",
    "violet" -> "673ab7",
    "darkblue" -> "3f51b5",
    "blue" -> "2196f3",
    "lightblue" -> "03a9f4",
    "aqua" -> "00bcd4",
    "cardgreen" -> "076324",
    "darkgreen" -> "009688",
    "green" -> "4caf50",
    "lightgreen" -> "8bc34a",
    "greenyellow" -> "cddc39",
    "yellow" -> "ffeb3b",
    "lightorange" -> "ffc107",
    "orange" -> "ff9800",
    "darkorange" -> "ff5722",
    "brown" -> "795548",
    "grey" -> "9e9e9e",
    "greyblue" -> "607d8b"
  )

  val srcDir = Paths.get(".", "util", "iconCreator", "src", "main", "resources", "icons", "svg")
  val tmpDir = Paths.get(".", "tmp", "icons")
  val outDir = Paths.get(".", "out")

  private val startMs = System.currentTimeMillis
  println("Creating icons...")
  go()
  println("Icon creation complete in [" + (System.currentTimeMillis - startMs) + "ms].")

  def go() = {
    wipeTarget()

    for(color <- colors) {
      for(icon <- icons) {
        val src = srcDir.resolve(icon + ".svg")
        val tgt = tmpDir.resolve(icon + "-" + color._1 + ".svg")
        colorizeSvg(src, tgt, color._2)
        convert(icon, color._1)
      }
      stitch(color._1)
      logos(color)
    }
  }

  private[this] def colorizeSvg(src: Path, tgt: Path, colorHex: String) = {
    val lines = readFileAsString(src)
    val output = lines.map { line =>
      if(line.startsWith("\t<path")) {
        line.replaceAllLiterally(
          "\t<path",
          s"""\t<path stroke="none" stroke-opacity="0.0" fill="#$colorHex" fill-opacity="1.0""""
        )
      } else {
        line
      }
    }.mkString("\n")
    writeFile(tgt, output)
  }

  private[this] def convert(icon: String, color: String) = {
    import scala.sys.process._
    println("Converting [" + icon + ":" + color + "]")
    if(icon == "cards" || icon == "check" || icon == "error") {
      s"convert -resize 500x500 -background none ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color-500.png".!
      s"convert -resize 64x64 -background none ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color@2x.png".!
      s"convert -resize 32x32 -background none ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color.png".!
    } else {
      s"convert -resize 500x500 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color-500.png".!
      s"convert -resize 64x64 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color@2x.png".!
      s"convert -resize 32x32 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color.png".!
    }
  }

  private[this] def stitch(color: String) = {
    import scala.sys.process._
    println("Stitching icons for [" + color + "]")


    val activeIconNames = icons.map(x => "./tmp/icons/" + x + "-white.png").mkString(" ")
    val inactiveIconNames = icons.map(x => "./tmp/icons/" + x + "-" + color + ".png").mkString(" ")
    val cmd = "montage " + activeIconNames + " " + inactiveIconNames + " -tile " + icons.size + "x2 -geometry +0 -background Transparent ./tmp/icons/icons-" + color + ".png"
    cmd.!

    val active2xIconNames = icons.map(x => "./tmp/icons/" + x + "-white@2x.png").mkString(" ")
    val inactive2xIconNames = icons.map(x => "./tmp/icons/" + x + "-" + color + "@2x.png").mkString(" ")
    val cmd2x = "montage " + active2xIconNames + " " + inactive2xIconNames + " -tile " + icons.size + "x2 -geometry +0 -background Transparent ./tmp/icons/icons-" + color + "@2x.png"
    cmd2x.!
  }

  private[this] def logos(color: (String, String)) = {
    import scala.sys.process._
    println("Creating logos for [" + color._1 + "]")

    s"convert -resize 600x600 -background #${color._2} ./tmp/icons/cards-white.svg ./tmp/icons/logo-${color._1}.png".!
    //s"convert ./tmp/icons/logo-${color._1}.png -gravity center -background #${color._2} -extent 600x600 ./tmp/icons/logo-${color._1}.png".!
    s"convert ./tmp/icons/logo-${color._1}.png -gravity center -background #${color._2} -extent 1920x1080 ./tmp/icons/splash-landscape-${color._1}.png".!
    s"convert ./tmp/icons/logo-${color._1}.png -gravity center -background #${color._2} -extent 1080x1920 ./tmp/icons/splash-portrait-${color._1}.png".!
    s"convert -resize 256x256 ./tmp/icons/logo-${color._1}.png ./tmp/icons/logo-${color._1}-256.png".!
    s"convert -resize 128x128 ./tmp/icons/logo-${color._1}.png ./tmp/icons/logo-${color._1}-128.png".!
    s"convert -resize 64x64 ./tmp/icons/logo-${color._1}.png ./tmp/icons/logo-${color._1}-64.png".!
    s"convert -resize 48x48 ./tmp/icons/logo-${color._1}.png ./tmp/icons/logo-${color._1}-48.png".!
    s"convert -resize 32x32 ./tmp/icons/logo-${color._1}.png ./tmp/icons/logo-${color._1}-32.png".!
    s"convert ./tmp/icons/logo-${color._1}-32.png ./tmp/icons/logo-${color._1}-64.png ./tmp/icons/logo-${color._1}-128.png ./tmp/icons/logo-${color._1}-256.png ./tmp/icons/logo-${color._1}.ico".!
  }

  private[this] def wipeTarget() = {
    val tgtStream = Files.newDirectoryStream(tmpDir)
    val tgtItr = collection.JavaConverters.asScalaIteratorConverter(tgtStream.iterator()).asScala
    for(p <- tgtItr) {
      Files.delete(p)
    }
  }

  private def readFileAsString(p: Path) = collection.JavaConverters.asScalaIteratorConverter(Files.readAllLines(p).iterator).asScala.toSeq
  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
