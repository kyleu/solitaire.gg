package models.rules.impl

import models.rules._

object Thoughtful extends GameRules(
  id = "thoughtful",
  completed = true,
  title = "Thoughtful",
  like = Some("klondike"),
  related = Seq("auntmary"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saratoga.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Klondike_(solitaire)")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
