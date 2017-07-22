package models.rules.impl

import models.rules._

object BigFreeCell extends GameRules(
  id = "bigfreecell",
  completed = true,
  title = "Big FreeCell",
  like = Some("freecell"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/big-freecell.htm")),
  layout = ":f|:c|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 8))
)
