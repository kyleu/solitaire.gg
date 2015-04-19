package utils.parser

object PolitaireCellParser {
  val titles = Seq(
    "C0n" -> "Number of cells",
    "C0Nm" -> "Cells name",
    "C0Ns" -> "Cell name",
    "C0o" -> "Can move cards to cell from",
    "C0d" -> "Card initially dealt into cells",
    "C0e" -> "Number of ephemeral cells"
  )

  val translations = Map(
    "c0n" -> Map(
      0 -> "0 cells", 1 -> "1 cell", 2 -> "2 cells", 3 -> "3 cells", 4 -> "4 cells", 5 -> "5 cells", 6 -> "6 cells", 7 -> "7 cells",
      8 -> "8 cells", 9 -> "9 cells", 10 -> "10 cells", 11 -> "11 cells", 12 -> "12 cells", 13 -> "13 cells", 14 -> "14 cells", 15 -> "15 cells"
    ),
    "C0o" -> Map(4 -> "Tableau", 16 -> "Cell"),
    "C0d" -> Map(
      1 -> "1 card", 2 -> "2 cards", 3 -> "3 cards", 4 -> "4 cards", 5 -> "5 cards", 6 -> "6 cards", 7 -> "7 cards", 8 -> "8 cards",
      9 -> "9 cards", 10 -> "10 cards", 11 -> "11 cards", 12 -> "12 cards", 13 -> "13 cards", 14 -> "14 cards", 15 -> "15 cards", 16 -> "16 cards"
    ),
    "C0e" -> Map(1 -> "1 cell", 2 -> "2 cells", 3 -> "3 cells", 4 -> "4 cells")
  )
}
