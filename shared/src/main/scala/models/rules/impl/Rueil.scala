package models.rules.impl

import models.rules._

object Rueil extends GameRules(
  id = "rueil",
  completed = false,
  title = "Rueil",
  like = Some("malmaison"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rueil.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
