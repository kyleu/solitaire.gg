package models.rules.impl

import models.rules._

object QuadrupleInterchange extends GameRules(
  id = "quadrupleinterchange",
  completed = true,
  title = "Quadruple Interchange",
  like = Some("tripleinterchange"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_interchange.htm")),
  layout = "",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(11),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
