package models.rules.impl

import models.rules._

object FortyDevils extends GameRules(
  id = "fortydevils",
  completed = false,
  title = "Forty Devils",
  like = Some("ladycadogan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_devils.htm")),
  layout = "swff|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Left Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits)
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
