package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Cells name (C0Nm): Reserve
 *   Cell name (C0Ns): Reserve
 *   Card initially dealt into cells (C0d): 8 (8 cards)
 *   Number of cells (C0n): 8
 *   Foundation name (F0Nm): Aces Foundation
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation name (F1Nm): Kings Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Deal order (RDd): 9 (Columns, left to right, top to bottom)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 24
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): buffalobill
 *   Number of decks (ndecks): 2 (2 decks)
 */
object LittleBillie extends GameRules(
  id = "littlebillie",
  completed = true,
  title = "Little Billie",
  like = Some("buffalobill"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_billie.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/little_billee.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleBillie.htm")
  ),
  layout = "::ff|::c|2t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 24,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(
    CellRules(
      name = "Reserve",
      pluralName = "Reserve",
      numPiles = 8,
      initialCards = 8
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
