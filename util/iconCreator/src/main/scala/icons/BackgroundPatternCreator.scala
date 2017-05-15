package icons

import scala.sys.process._

object BackgroundPatternCreator {
  val srcDir = new java.io.File("public/images/background")
  val tmpDir = new java.io.File("tmp/patterns")
  val tgtDir = new java.io.File("public/images/settings")

  def main(args: Array[String]): Unit = {
    go()
  }

  def go() = {
    tmpDir.mkdirs()

    val files = srcDir.listFiles().filter(_.getName.endsWith(".png"))
    files.map { f =>
      s"convert -size 32x32 -background none tile:./public/images/background/${f.getName} ./tmp/patterns/${f.getName}".!
    }

    val names = files.filterNot(_.getName == "none.png").map("./tmp/patterns/" + _.getName).sorted.mkString(" ")
    s"montage -mode concatenate -background none -tile x1 -geometry +0+0 -size 32x32 ./tmp/patterns/none.png $names ./tmp/patterns.png".!
  }
}
