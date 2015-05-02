package parser.politaire.lookup

object GameLookup {
  val titles = Seq(
    "title" -> "Title",
    "desc" -> "Description",
    "victory" -> "Victory condition",
    "pairs" -> "Card removal method",
    "like" -> "Similar to",

    "Fn" -> "Foundation Sets",
    "Tn" -> "Tableau sets",
    "Pn" -> "Number of pyramids",
    "Sn" -> "Enable stock",

    "RDn" -> "Number of redeals allowed",
    "RDp" -> "Pickup order",
    "RDs" -> "Shuffle before redealing",
    "RDd" -> "Deal order",

    "nrot" -> "Number of rotations allowed",
    "toptobot" -> "Rotation direction",

    "ndraw" -> "Number of draws allowed",
    "drawrule" -> "Draws must be after redeals"
  )

  private val orderTranslations = Map(
    9 -> "Columns, left to right, top to bottom",
    1 -> "Columns, left to right, bottom to top",
    11 -> "Columns, right to left, top to bottom",
    3 -> "Columns, right to left, bottom to top",
    0 -> "Rows, left to right, bottom to top",
    2 -> "Rows, right to left, bottom to top",
    6 -> "Rows, zig-zag, bottom to top"
  )

  val translations = Map(
    "victory" -> Map(
      0 -> "All cards on foundation",
      1 -> "All but 4 cards per deck on foundation",
      5 -> "All cards on foundation or stock",
      2 -> "No cards left in stock",
      3 -> "All cards on tableau sorted"
    ),
    "pairs" -> Map(
      0 -> "Build sequences on foundation",
      1 -> "Remove pairs of same rank",
      2 -> "Remove pairs of same rank and color",
      21 -> "Remove pairs of same suit",
      3 -> "Remove nines, pairs adding to 9, or 10-J-Q-K",
      4 -> "Remove pairs adding to 10, 10-10, J-J, Q-Q, or K-K",
      6 -> "Remove pairs adding to 10, or four 10s, Js, Qs, or Ks",
      23 -> "Remove pairs adding to 11, J-J or Q-K",
      7 -> "Remove pairs adding to 11, J-J, Q-Q, or K-K",
      8 -> "Remove pairs adding to 11 or J-Q-K",
      9 -> "Remove same suit pairs adding to 11 or J-Q-K",
      18 -> "Remove pairs adding to 12 or Q-K",
      10 -> "Remove kings or pairs adding to 13",
      11 -> "Remove pairs adding to 14",
      12 -> "Remove sets adding to 15 or 10-J-Q-K",
      24 -> "Remove sets adding to 15 or four 10s, Js, Qs, or Ks",
      13 -> "Remove pairs adding to 15 or A-A",
      14 -> "Remove pairs adding to 17 or A-2-3",
      15 -> "Remove sets of 1 face card and 3 that add to 18",
      16 -> "Remove pairs with consecutive ranks",
      22 -> "Remove pairs with consecutive ranks or A-K",
      17 -> "Remove pairs with consecutive or equal ranks",
      20 -> "Stack cards of same rank/suit in waste"
    ),
    "Sn" -> Map(0 -> "No stock", 1 -> "1 stock"),
    "Tn" -> Map(0 -> "0 tableau sets", 1 -> "1 tableau set", 2 -> "2 tableau sets", 3 -> "3 tableau sets", 4 -> "4 tableau sets"),
    "Pn" -> Map(0 -> "0 pyramids", 1 -> "1 pyramid", 2 -> "2 pyramids", 3 -> "3 pyramids", 4 -> "4 pyramids"),
    "RDs" -> Map(0 -> "No", 1 -> "Yes", 3 -> "Yes"),
    "RDp" -> orderTranslations,
    "RDd" -> orderTranslations,
    "toptobot" -> Map(0 -> "Top to bottom", 1 -> "Bottom to top")
  )
}
