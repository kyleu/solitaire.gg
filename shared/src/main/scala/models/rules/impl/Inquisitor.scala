package models.rules.impl

import models.rules._

object Inquisitor extends GameRules(
  id = "inquisitor",
  completed = true,
  title = "Inquisitor",
  like = Some("ladyjane"),
  related = Seq("quizzie"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/inquisitor.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
