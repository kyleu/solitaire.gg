package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Ace Foundation
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation name (F1Nm): King Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): -1
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Tableau action during deal (T0dd): 3 (Move cards to foundations)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): caprice
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Nationale extends GameRules(
  id = "nationale",
  completed = false,
  title = "Nationale",
  like = Some("caprice"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/nationale.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/nationale.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/nationale.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Nationale.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/nationale.php")
  ),
  description = "Like ^caprice^ without a stock.",
  layout = "ff|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
