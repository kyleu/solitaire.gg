package models.rules.impl

import models.rules._

object KlondikeGalleryMode extends GameRules(
  id = "klondikegallery",
  completed = false,
  title = "Klondike (Gallery Mode)",
  like = Some("klondike"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Klondike_(solitaire)"),
    Link("Robert Abbott's Strategy Guide", "www.logicmazes.com/sol/")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
