package models.rules.impl

import models.card.{Red, Black}

import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Red Foundation
 *   Foundation initial cards (F0d): 2 (2 cards)
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Initial card restriction (F0u): 3 (Unique red suits)
 *   Foundation name (F1Nm): Black Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 2 (2 cards)
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 4 (Unique black suits)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 9 (Kings or aces only)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): cloverleaf
 */
object AlexanderTheGreat extends GameRules(
  id = "alexanderthegreat",
  completed = true,
  title = "Alexander the Great",
  related = Seq("cloverleaf"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alexander_the_great.htm")),
  description = "Thomas Warfield's more challenging variation of ^cloverleaf^.",
  layout = "f::f|2t",
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Red)),
      initialCards = 2,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Black)),
      initialCards = 2,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRankOrLowRank
    )
  )
)
