package models.rules.impl

import models.card.Suit
import models.rules._

object ChineseFreeCell extends GameRules(
  id = "chinesefreecell",
  completed = true,
  title = "Chinese FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_freecell.htm")),
  layout = "f:c|:t",
  deckOptions = DeckOptions(numDecks = 2, suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
