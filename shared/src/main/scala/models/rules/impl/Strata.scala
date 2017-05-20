package models.rules.impl

import models.card.Rank
import models.rules._

object Strata extends GameRules(
  id = "strata",
  completed = true,
  title = "Strata",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/strata.htm")),
  layout = "wf|:t",
  deckOptions = DeckOptions(numDecks = 2, ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
