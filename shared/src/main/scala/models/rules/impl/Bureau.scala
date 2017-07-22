package models.rules.impl

import models.rules._

object Bureau extends GameRules(
  id = "bureau",
  completed = true,
  title = "Bureau",
  like = Some("athena"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bureau.htm")),
  layout = "sw.f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, suitMatchRule = SuitMatchRule.AlternatingColors, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
