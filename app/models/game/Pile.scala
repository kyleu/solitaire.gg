package models.game

object Pile {
  val default = List(
    Pile("stock"),
    Pile("waste"),

    Pile("foundation-1"),
    Pile("foundation-2"),
    Pile("foundation-3"),
    Pile("foundation-4"),

    Pile("tableau-1"),
    Pile("tableau-2"),
    Pile("tableau-3"),
    Pile("tableau-4"),
    Pile("tableau-5"),
    Pile("tableau-6"),
    Pile("tableau-7")
  )
}

case class Pile(
  id: String,
  var cards: List[Card] = Nil
)
