package models.ui

import scala.util.Random

object Colors {
  val all = Seq(
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

  def randomColor = all(Random.nextInt(all.size))
}
