package utils.parser.politaire.lookup

object StockLookup {
  val titles = Seq(
    "smode" -> "Stock pile",
    "S0Nm" -> "Stock name",
    "dealto" -> "Deal cards from stock",
    "maxdeals" -> "Maximum deals from stock",
    "dealchunk" -> "Deal cards from stock",
    "wrapdeal" -> "If too few cards in stock",
    "millres" -> "Create pocket when stock runs out"
  )
  val translations = Map(
    // Stock
    "smode" -> Map(1 -> "Normal", 0 -> "None", -1 -> "Gallery Mode"),
    "dealto" -> Map(
      1 -> "To all waste piles", 2 -> "To all tableau piles", 3 -> "To all tableau piles if none empty", 4 -> "To all non-empty tableau piles",
      6 -> "To all foundation piles", 7 -> "Manually", 8 -> "Never"
    ),
    "maxdeals" -> Map(1 -> "1", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 10 -> "Unlimited"),
    "dealchunk" -> Map(
      1 -> "One by one", 2 -> "Two at a time", 3 -> "Three at a time", 4 -> "Four at a time", 5 -> "Five at a time", 6 -> "Six at a time",
      7 -> "Seven at a time", 8 -> "Eight at a time", -1 -> "Fewer in each pass"
    ),
    "wrapdeal" -> Map(0 -> "Stop after partial deal", 1 -> "Start new pass during deal"),
    "millres" -> Map(0 -> "No", 1 -> "For one card", 2 -> "For one stack of cards")
  )
}
