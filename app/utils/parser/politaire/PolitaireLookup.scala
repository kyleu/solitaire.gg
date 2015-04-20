package utils.parser.politaire

object PolitaireLookup {
  val titleTable =
    GameParser.titles ++
      DeckParser.titles ++
      FoundationParser.titles ++
      TableauParser.titles ++
      StockParser.titles ++
      WasteParser.titles ++
      ReserveParser.titles ++
      CellParser.titles ++
      PyramidParser.titles ++
      Seq(
        // Special
        "RDn" -> "Allowed pick ups/redeals",
        "nrot" -> "Allowed tableau rotations",
        "ndraw" -> "Allowed draws"
      )

  val titleMap = titleTable.toMap
}
