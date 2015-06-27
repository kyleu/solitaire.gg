// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Cells name (C0Nm): Reserve
 *   Cell name (C0Ns): Reserve
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
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 26
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): littlebillie
 */
object BuffaloBill extends GameRules(
  id = "buffalobill",
  title = "Buffalo Bill",
  related = Seq("littlebillie"),
  links = Seq(
    Link("David Parlett's Page", "www.davpar.eu/patience/buffbill.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/buffalo_bill.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/buffalo_bill.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/buffalo-bill.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/buffalo_bill.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BuffaloBill.htm")
  ),
  description = "In this easy variation of ^littlebillie^, by David Parlett, there are more fans and the reserve cells start empty, but there are n" +
    "o redeals.",
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
      numPiles = 26,
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
      numPiles = 8
    )
  )
)
