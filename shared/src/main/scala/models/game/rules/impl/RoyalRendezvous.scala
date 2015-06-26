// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Straight Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation name (F1Nm): Odd Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation initial cards (F1d): -1
 *   Maximum cards for foundation (F1m): 6
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation name (F2Nm): Even Foundation
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation low rank (F2b): 2 (2)
 *   Foundation initial cards (F2d): -1
 *   Maximum cards for foundation (F2m): 6
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation rank match rule (F2r): 256 (Build up by 2)
 *   Initial card restriction (F2u): 2 (Unique suits)
 *   Foundation name (F3Nm): King Foundation
 *   Auto-move cards to foundation (F3a): 0 (Never)
 *   Foundation low rank (F3b): 13
 *   Maximum cards for foundation (F3m): 1
 *   Number of foundation piles (F3n): 4 (4 stacks)
 *   Initial card restriction (F3u): 2 (Unique suits)
 *   Foundation Sets (Fn): 4
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object RoyalRendezvous extends GameRules(
  id = "royalrendezvous",
  completed = true,
  title = "Royal Rendezvous",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Royal_Rendezvous"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_rendezvous.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/royal_rendezvous.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RoyalRendezvous.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/royal_rendezvous.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-rendezvous.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RoyalRendezvous.htm")
  ),
  description = "An odd Austrian game with four foundation sets, one normal, one for evens, one for odds, and one for kings. No building on the tab" +
    "leau.",
  layout = Some("sw:ff|::::ff|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Straight Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4
    ),
    FoundationRules(
      name = "Odd Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
    ),
    FoundationRules(
      name = "Even Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      maxCards = 1
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
