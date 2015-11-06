package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 5
 *   Foundation initial cards (F0d): 3 (3 cards)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Piles with low cards at bottom (T0dc): 1 (1 columns)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): penguin
 *   Low card (lowpip): -2 (?)
 */
object Opus extends GameRules(
  id = "opus",
  completed = true,
  title = "Opus",
  like = Some("penguin"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/opus.htm")),
  description = "Thomas Warfield's much more difficult version of ^penguin^ has two fewer cells",
  layout = ".:f|:c|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 3,
      autoMoveCards = true
    )
  ),
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
  cells = Some(
    CellRules(
      numPiles = 5
    )
  )
)
