package icons

import better.files._

object CardImageCreator {
  val srcDir = "public/images/cards".toFile

  def main(args: Array[String]): Unit = {
    go()
  }

  def go() = {
    val src = srcDir / "hd"
    process(src, srcDir / "large", 50)
    process(src, srcDir / "small", 25)
  }

  def process(src: File, dest: File, percentage: Int) = {
    dest.delete(true)
    dest.createDirectory()

    src.list.filter(_.isDirectory).foreach { sd =>
      val dd = dest / sd.name
      dd.createDirectory()
      sd.list.filter(_.name.endsWith(".png")).foreach { f =>
        f.copyTo(dd / f.name)
      }
    }
  }
}
