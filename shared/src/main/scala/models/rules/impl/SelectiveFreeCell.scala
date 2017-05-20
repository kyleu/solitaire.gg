package models.rules.impl

import models.card.Rank
import models.rules._

object SelectiveFreeCell extends GameRules(
  id = "selectivefreecell",
  completed = false,
  title = "Selective FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/selective_freecell.htm")),
  layout = "f|c|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
