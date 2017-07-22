package models.rules.impl

import models.rules._

object FreeCellFourDeck extends GameRules(
  id = "freecellfourdeck",
  completed = true,
  title = "FreeCell Four Deck",
  like = Some("freecellthreedeck"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/freecell_four_decks.htm")),
  layout = "f|:c|:t",
  deckOptions = DeckOptions(numDecks = 4),
  foundations = IndexedSeq(FoundationRules(numPiles = 16, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 14))
)
