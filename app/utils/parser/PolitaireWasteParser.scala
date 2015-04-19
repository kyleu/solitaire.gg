package utils.parser

object PolitaireWasteParser {
  val titles = Seq(
    "W0n" -> "Number of waste piles",
    "W0Nm" -> "Waste name",
    "W0a" -> "Playable waste cards"
  )

  val translations = Map(
    "w0n" -> Map(0 -> "0 wastes", 1 -> "1 waste", 2 -> "2 wastes", 3 -> "3 wastes", 4 -> "4 wastes"),
    "W0a" -> Map(0 -> "Top card only", 1 -> "All cards")
  )
}
