package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Congress extends GameRules(
  id = "congress",
  completed = false,
  title = "Congress",
  related = Seq("parliament", "diplomat", "dieppe"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Congress_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/congress.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/congress.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/congress.html"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#congress")
  ),
  layout = "sw|f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  )
)
