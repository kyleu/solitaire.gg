package parser.politaire

object PolitaireParserHelper {
  val pileTypes = Map(
    1 -> "Stock", 2 -> "Waste", 4 -> "Tableau", 8 -> "Foundation", 16 -> "Cell", 32 -> "Pocket", 64 -> "Reserve", 128 -> "Pyramid"
  )
  val suits = Map(
    1 -> "S", 2 -> "H", 4 -> "C", 8 -> "D", 16 -> "G" /* Clubs (Green) */ , 32 -> "B" /* Diamonds (Blue) */ ,
    64 -> "O" /* Horseshoes (Black) */ , 128 -> "R" /* Stars (Red) */ , 256 -> "T" /* Tridents (Green) */ , 512 -> "M" /* Moons (Blue) */ ,
    1024 -> "X" /* Suitless */
  )
  val ranks = Map(
    1 -> "A", 2 -> "2", 4 -> "3", 8 -> "4", 16 -> "5", 32 -> "6", 64 -> "7", 128 -> "8", 256 -> "9", 512 -> "X", 1024 -> "J", 2048 -> "Q", 4096 -> "K"
  )
}
