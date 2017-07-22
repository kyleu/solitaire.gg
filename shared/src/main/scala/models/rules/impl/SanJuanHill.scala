package models.rules.impl

import models.rules._

object SanJuanHill extends GameRules(
  id = "sanjuanhill",
  completed = false,
  title = "San Juan Hill",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/san_juan_hill.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SanJuanHill.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Hill",
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Rough Riders",
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
