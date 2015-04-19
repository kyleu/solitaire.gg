package utils.parser

object PolitaireTranslations {
  private[this] val translationTable = {
    PolitaireGameParser.translations ++
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

  def getTranslation(key: String) = {
    val c = key(1)
    if(c == '1' || c == '2' || c == '3' || c == '4') {
      translationTable.get(key.head + "0" + key.tail.tail)
    } else {
      translationTable.get(key)
    }
  }

  def parseBitmask(key: String, i: Int) = {
    val translations = translationTable(key)
    translations.flatMap { t =>
      if((t._1 & i) != 0) {
        Some(t._2)
      } else {
        None
      }
    }
  }
}
