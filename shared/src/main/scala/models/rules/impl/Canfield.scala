package models.rules.impl

import models.card.Rank
import models.rules._

object Canfield extends GameRules(
  id = "canfield",
  completed = true,
  title = "Canfield",
  related = Seq("rainbow", "storehouse", "acme", "canfieldgallery", "superiorcanfield", "canfieldrush", "demonsandthieves", "chameleon"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Canfield_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canfield.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Canfield.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/canfield.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Canfield.html.en"),
    Link("Solitaire City", "www.solitairecity.com/Help/Demon.shtml"),
    Link("Jan Wolter's Experiments", "/article/canfield.html")
  ),
  layout = "swf|:r:t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
