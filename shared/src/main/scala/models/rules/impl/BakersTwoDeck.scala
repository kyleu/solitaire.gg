package models.rules.impl

import models.rules._

object BakersTwoDeck extends GameRules(
  id = "bakerstwodeck",
  completed = false,
  title = "Baker's Two-Deck",
  like = Some("bakers"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bakersgame2.htm")),
  layout = "f|c|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 8))
)
