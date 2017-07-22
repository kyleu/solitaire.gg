package models.rules.impl

import models.rules._

object FortyThieves4Deck extends GameRules(
  id = "fortythieves4",
  completed = true,
  title = "Forty Thieves (4 deck)",
  like = Some("fortythieves"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/forty_thieves_four_decks.htm")),
  layout = "swf|.::t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
