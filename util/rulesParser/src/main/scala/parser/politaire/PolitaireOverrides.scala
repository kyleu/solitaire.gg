package parser.politaire

object PolitaireOverrides {
  private[this] def o(s: String, m: (String, Int)*) = s -> m.toMap

  val overrides = Map(
    o("accordion", "W0cardsShown" -> 52),
    o("bakersdozen", "Sn" -> 0, "Tn" -> 2, "T0n" -> 7, "T1n" -> 6, "T1s" -> 5, "T1f" -> 5, "T1dd" -> 1),
    o("bisley", "Sn" -> 0, "W0n" -> 0, "Tn" -> 2, "T0n" -> 4, "T0d" -> 3, "T1n" -> 9, "T1d" -> 4, "T1r" -> 160, "T1f" -> 5),
    o("deucesandqueens", "F0u" -> 0),
    o("flowergarden", "W0cardsShown" -> 20),
    o("fourteenout", "F0n" -> 1, "F0i" -> 1),
    o("golf", "S0cardsShown" -> 16),
    o("goodmeasure", "Tn" -> 1),
    o("pyramid", "F0n" -> 1, "dealto" -> 1),
    o("royalrendezvous", "T0f" -> 0),
    o("thoughtful", "T0d" -> -3),
    o("trustytwelve", "S0cardsShown" -> 19, "T0cardsShown" -> 2)
  )
}
