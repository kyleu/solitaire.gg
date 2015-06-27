// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Aces Foundation
 *   Foundation initial cards (F0d): 4 (4 cards)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation name (F1Nm): Kings Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 4 (4 cards)
 *   Can move cards from foundation (F1mb): true
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   May move to non-empty tableau from (T0o): 4 (tableau)
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): rainbowfan
 *   Number of decks (ndecks): 2 (2 decks)
 *   Allowed tableau rotations (nrot): 3 (3)
 *   Related games (related): crescentfour
 *   Rotation direction (toptobot): false
 */
object Crescent extends GameRules(
  id = "crescent",
  title = "Crescent",
  like = Some("rainbowfan"),
  related = Seq("crescentfour"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Crescent_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/crescent.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/crescent.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/crescent.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/crescent.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Crescent.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/crescent.htm"),
    Link("Lena Games", "www.lenagames.com/bp_files/rul/crescent.htm")
  ),
  description = "A bidirectional building game where you can rotate cards in the stacks three times.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
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
      numPiles = 16,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq("tableau")
    )
  ),
  special = Some(
    SpecialRules(
      shuffleBeforeRedeal = false,
      rotationsAllowed = 3,
      rotationTopToBottom = false
    )
  )
)
