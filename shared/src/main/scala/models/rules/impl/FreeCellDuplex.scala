package models.rules.impl

import models.rules._

object FreeCellDuplex extends GameRules(
  id = "freecellduplex",
  completed = true,
  title = "FreeCell Duplex",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/freecell_duplex.htm")),
  layout = "::f|::c|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 8))
)
