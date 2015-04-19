package utils.parser

object PolitaireDeckParser {
  val titles = Seq(
    "ndecks" -> "Number of decks",
    "stdsuits" -> "Suits in use",
    "suits" -> "Custom suits",
    "ranks" -> "Ranks in use",
    "lowpip" -> "Low card"
  )

  val translations = Map(
    "ndecks" -> Map(
      1 -> "1 deck", 2 -> "2 decks", 3 -> "3 decks", 4 -> "4 decks", 5 -> "5 decks", 6 -> "6 decks", 7 -> "7 decks", 8 -> "8 decks",
      9 -> "9 decks", 10 -> "10 decks", 11 -> "11 decks", 12 -> "12 decks", 13 -> "13 decks", 14 -> "14 decks", 15 -> "15 decks", 16 -> "16 decks"
    ),
    "stdsuits" -> Map(
      0 -> "Auto-optimized Standard",
      15 -> "4 Suits in 2 Colors (Standard)",
      51 -> "4 Suits in 4 Colors",
      35 -> "3 Suits in 3 Colors",
      3 -> "2 Suits in 2 Colors",
      399 -> "6 Suits in 2 Colors",
      483 -> "6 Suits in 3 Colors",
      1011 -> "8 Suits in 4 Colors",
      -1 -> "Custom"
    ),
    "lowpip" -> Map(
      1 -> "A", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "X",
      11 -> "J", 12 -> "Q", 13 -> "K", -2 -> "?" /* First card to foundation */, -1 -> "." /* Circular */
    )
  )
}
