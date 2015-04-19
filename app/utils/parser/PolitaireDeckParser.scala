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
      1 -> "Ace", 2 -> "Two", 3 -> "Three", 4 -> "Four", 5 -> "Five", 6 -> "Six", 7 -> "Seven", 8 -> "Eight", 9 -> "Nine", 10 -> "Ten",
      11 -> "Jack", 12 -> "Queen", 13 -> "King", -2 -> "First card to foundation", -1 -> "Circular"
    )
  ) ++ Map(
    // Foundation
    "F0b" -> Map(
      21 -> "Deck's low card", 22 -> "Deck's high card",
      1 -> "Ace", 2 -> "Two", 3 -> "Three", 4 -> "Four", 5 -> "Five", 6 -> "Six", 7 -> "Seven", 8 -> "Eight", 9 -> "Nine", 10 -> "Ten",
      11 -> "Jack", 12 -> "Queen", 23 -> "Ace, Two, Thre...", 20 -> "Any Card"
    ),
    "F0d" -> Map(0 -> "None", 1 -> "1 cards", 2 -> "2 cards", 3 -> "3 cards", 4 -> "4 cards"),
    "F0s" -> Map(1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"),
    "F0r" -> Map(
      128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "FOw" -> Map(0 -> "No", 1 -> "Yes"),
    "F0cs" -> Map(0 -> "No", 1 -> "Yes"),
    "F0mb" -> Map(0 -> "Never", 1 -> "Always", 2 -> "Once the stock is empty"),
    "F0o" -> Map(2 -> "Waste", 4 -> "Tableau", 128 -> "Pyramid", 8 -> "Foundation"),
    "F0i" -> Map(0 -> "No", 1 -> "Yes"),
    "F0a" -> Map(
      0 -> "Never", 4 -> "Keeping piles level", 3 -> "When all stackable cards are off", 2 -> "When one stackable card is off",
      5 -> "When stackable cards are removable", 1 -> "Whenever possible"
    ),
    "F0ao" -> Map(2 -> "Waste", 4 -> "Tableau", 128 -> "Pyramid", 8 -> "Foundation")
  )
}
