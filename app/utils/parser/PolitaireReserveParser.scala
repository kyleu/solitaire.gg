package utils.parser

object PolitaireReserveParser {
  val titles = Seq(
    "R0n" -> "Number of reserve piles",
    "R0d" -> "Reserve initial cards",
    "R0df" -> "Reserve cards face down"
  )

  val translations = Map(
    "r0n" -> Map(
      0 -> "0 reserves", 1 -> "1 reserve", 2 -> "2 reserves", 3 -> "3 reserves", 4 -> "4 reserves",
      5 -> "5 reserves", 6 -> "6 reserves", 7 -> "7 reserves", 8 -> "8 reserves"
    )
  )
}
