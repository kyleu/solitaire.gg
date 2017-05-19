package models.rules.impl

import models.rules._

object DoubleKlondike extends GameRules(
  id = "doubleklondike",
  completed = true,
  title = "Double Klondike",
  related = Seq("sally", "suittriangle"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_klondike.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/double_klondike.htm"),
    Link("Dan Fletcher's How To Play", "www.solitairecentral.com/articles/HowToPlayDoubleKlondike.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Jumbo.html.en")
  ),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 9, emptyFilledWith = FillEmptyWith.HighRank))
)
