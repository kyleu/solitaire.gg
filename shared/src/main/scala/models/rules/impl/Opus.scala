package models.rules.impl

import models.card.Rank
import models.rules._

object Opus extends GameRules(
  id = "opus",
  completed = true,
  title = "Opus",
  like = Some("penguin"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/opus.htm")),
  layout = ".:f|:c|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 3, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank,
      pilesWithLowCardsAtBottom = 1
    )
  ),
  cells = Some(CellRules(numPiles = 5))
)
