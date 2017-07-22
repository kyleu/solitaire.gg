package models.rules.impl

import models.rules._

object TripleFreeCell extends GameRules(
  id = "triplefreecell",
  completed = true,
  title = "Triple FreeCell",
  related = Seq("celleleven", "freecellthreedeck"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_freecell.htm")),
  layout = ".f|:.c|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 10))
)
