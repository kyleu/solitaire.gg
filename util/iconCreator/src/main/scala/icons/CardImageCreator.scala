package icons

object CardImageCreator {
  val srcDir = new java.io.File("public/images/cards")

  def main(args: Array[String]): Unit = {
    go()
  }

  def go() = {
    println("Creating card images...")
  }
}
