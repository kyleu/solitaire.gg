package models.rules.impl

import models.rules._

object Gypsy extends GameRules(
  id = "gypsy",
  completed = true,
  title = "Gypsy",
  related = Seq("irmgard"),
  links = Seq(
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/gypsy.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Gypsy.html.en"),
    Link("KPatience", "docs.kde.org/stable/en/kdegames/kpat/rules-specific.html#gypsy")
  ),
  layout = "sf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3)
    )
  )
)
