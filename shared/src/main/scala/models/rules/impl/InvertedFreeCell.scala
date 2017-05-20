package models.rules.impl

import models.rules._

object InvertedFreeCell extends GameRules(
  id = "invertedfreecell",
  completed = false,
  title = "Inverted FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/inverted_freecell.htm")),
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
