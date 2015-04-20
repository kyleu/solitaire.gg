package utils.parser

object PolitaireLookup {
  val titleTable =
    PolitaireGameParser.titles ++
      PolitaireDeckParser.titles ++
      PolitaireFoundationParser.titles ++
      PolitaireTableauParser.titles ++
      PolitaireStockParser.titles ++
      PolitaireWasteParser.titles ++
      PolitaireReserveParser.titles ++
      PolitaireCellParser.titles ++
      PolitairePyramidParser.titles ++
      Seq(
        // Special
        "RDn" -> "Allowed pick ups/redeals",
        "nrot" -> "Allowed tableau rotations",
        "ndraw" -> "Allowed draws"
      )

  val titleMap = titleTable.toMap
}
