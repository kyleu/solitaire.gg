package models.rules.impl

import models.rules._

object FreeCellThreeDeck extends GameRules(
  id = "freecellthreedeck",
  completed = true,
  title = "FreeCell Three Deck",
  like = Some("triplefreecell"),
  related = Seq("freecellfourdeck"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/freecell_three_decks.htm")),
  layout = "f|c|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 12))
)
