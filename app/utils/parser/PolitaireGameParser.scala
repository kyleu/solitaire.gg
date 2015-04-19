package utils.parser

object PolitaireGameParser {
  val titles = Seq(
    "title" -> "Title",
    "desc" -> "Description",
    "victory" -> "Victory condition",
    "pairs" -> "Card removal method"
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
    )
  )
}
