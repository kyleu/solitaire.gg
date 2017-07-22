package models.rules.impl

import models.rules._

object DoubleFreeCell extends GameRules(
  id = "doublefreecell",
  completed = true,
  title = "Double FreeCell",
  like = Some("freecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_freecell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/double_freecell.htm")
  ),
  layout = "f:c|.t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 6))
)
