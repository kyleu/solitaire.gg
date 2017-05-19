package models.rules.impl

import models.rules._

object CellEleven extends GameRules(
  id = "celleleven",
  completed = true,
  title = "Cell Eleven",
  like = Some("triplefreecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cell_11.htm")),
  layout = "f|.c|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = Seq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 11, initialCards = 2))
)
