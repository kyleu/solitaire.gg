package models.rules.impl

import models.rules._

object Giant extends GameRules(
  id = "giant",
  completed = true,
  title = "Giant",
  related = Seq("titan"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/giant.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/giant.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/giant.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/giant.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/giant.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Giant.html.en")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      canMoveFrom = FoundationCanMoveFrom.EmptyStock,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
