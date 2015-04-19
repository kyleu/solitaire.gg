package utils.parser

object PolitaireTranslations {
  val translationTable = PolitaireGameParser.translations ++
    PolitaireDeckParser.translations ++
    PolitaireFoundationParser.translations ++
    PolitaireTableauParser.translations ++
    PolitaireStockParser.translations ++
    PolitaireWasteParser.translations ++
    PolitaireReserveParser.translations ++
    PolitaireCellParser.translations ++
    PolitairePyramidParser.translations ++
    Map(
      // Special
      "RDn" -> Map(1 -> "1", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "10", -1 -> "Unlimited"),
      "nrot" -> Map(1 -> "1", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "10", -1 -> "Unlimited"),
      "ndraw" -> Map(1 -> "1", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "10", -1 -> "Unlimited")
    )
}
