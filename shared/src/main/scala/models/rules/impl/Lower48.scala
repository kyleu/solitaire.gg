package models.rules.impl

import models.rules._

object Lower48 extends GameRules(
  id = "lower48",
  completed = false,
  title = "Lower 48",
  like = Some("fortyandeight"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lower_48.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
