package icons

import java.nio.file.{ Files, Path, Paths }

object IconCreator extends App {
  private val icons = Seq("back", "next", "hint", "options", "profile", "menu", "help", "edit")
  private val colors = Seq(
    "ffffff", "000000", "f44336", "e91e63", "9c27b0", "673ab7", "3f51b5", "2196f3", "03a9f4", "00bcd4", "009688",
    "4caf50", "8bc34a", "cddc39", "ffeb3b", "ffc107", "ff9800", "ff5722", "795548", "9e9e9e", "607d8b", "076324"
  )

  val srcDir = Paths.get(".", "util", "iconCreator", "src", "main", "resources", "icons", "svg")
  val tgtDir = Paths.get(".", "tmp", "icons")

  private val startMs = System.currentTimeMillis
  println("Creating icons...")
  go()
  println("Icon creation complete in [" + (System.currentTimeMillis - startMs) + "ms].")

  def go() = {
    wipeTarget()

    for(color <- colors) {
      for(icon <- icons) {
        val src = srcDir.resolve(icon + ".svg")
        val tgt = tgtDir.resolve(icon + "-" + color + ".svg")
        colorizeSvg(src, tgt, color)
        convert(icon, color)
      }
      stitch(color)
    }
  }

  private[this] def colorizeSvg(src: Path, tgt: Path, color: String) = {
    val lines = readFileAsString(src)
    val output = lines.map { line =>
      if(line.startsWith("\t<path")) {
        line.replaceAllLiterally(
          "\t<path",
          s"""\t<path stroke="none" stroke-opacity="0.0" fill="#$color" fill-opacity="1.0""""
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
    s"convert -resize 500x500 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color-500.png".!
    s"convert -resize 64x64 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color@2x.png".!
    s"convert -resize 32x32 -background none -trim ./tmp/icons/$icon-$color.svg ./tmp/icons/$icon-$color.png".!
  }

  private[this] def stitch(color: String) = {
    import scala.sys.process._
    println("Stitching icons for [" + color + "]")

    val activeIconNames = icons.map(x => "./tmp/icons/" + x + "-ffffff.png").mkString(" ")
    val inactiveIconNames = icons.map(x => "./tmp/icons/" + x + "-" + color + ".png").mkString(" ")
    val cmd = "montage " + activeIconNames + " " + inactiveIconNames + " -tile " + icons.size + "x2 -geometry +0 -background Transparent ./tmp/icons/icons-" + color + ".png"
    cmd.!

    val active2xIconNames = icons.map(x => "./tmp/icons/" + x + "-ffffff@2x.png").mkString(" ")
    val inactive2xIconNames = icons.map(x => "./tmp/icons/" + x + "-" + color + "@2x.png").mkString(" ")
    val cmd2x = "montage " + active2xIconNames + " " + inactive2xIconNames + " -tile " + icons.size + "x2 -geometry +0 -background Transparent ./tmp/icons/icons-" + color + "@2x.png"
    cmd2x.!
  }

  private[this] def wipeTarget() = {
    val tgtStream = Files.newDirectoryStream(tgtDir)
    val tgtItr = collection.JavaConverters.asScalaIteratorConverter(tgtStream.iterator()).asScala
    for(p <- tgtItr) {
      Files.delete(p)
    }
  }

  private def readFileAsString(p: Path) = collection.JavaConverters.asScalaIteratorConverter(Files.readAllLines(p).iterator).asScala.toSeq
  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
