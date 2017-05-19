package models.rules.impl

import models.rules._

object FortyThieves3Deck extends GameRules(
  id = "fortythieves3",
  completed = true,
  title = "Forty Thieves (3 deck)",
  like = Some("fortythieves"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/forty_thieves_three_decks.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
