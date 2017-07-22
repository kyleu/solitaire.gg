package models.rules.impl

import models.rules._

object TripleInterchange extends GameRules(
  id = "tripleinterchange",
  completed = true,
  title = "Triple Interchange",
  like = Some("interchange"),
  related = Seq("quadrupleinterchange"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_interchange.htm")),
  layout = ":::::sw|f|::t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(9),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
